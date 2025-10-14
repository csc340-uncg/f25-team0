package com.csc340.localharvest_hub.mvc.controller;
import com.csc340.localharvest_hub.customer.Customer;
import com.csc340.localharvest_hub.customer.CustomerService;
import com.csc340.localharvest_hub.producebox.ProduceBox;
import com.csc340.localharvest_hub.producebox.ProduceBoxService;
import com.csc340.localharvest_hub.review.Review;
import com.csc340.localharvest_hub.review.ReviewService;
import com.csc340.localharvest_hub.subscription.Subscription;
import com.csc340.localharvest_hub.subscription.SubscriptionService;
import com.csc340.localharvest_hub.subscription.SubscriptionType;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerMvcController {
    private final CustomerService customerService;
    private final ProduceBoxService produceBoxService;
    private final SubscriptionService subscriptionService;
    private final ReviewService reviewService;

    public CustomerMvcController(CustomerService customerService,
                               ProduceBoxService produceBoxService,
                               SubscriptionService subscriptionService,
                               ReviewService reviewService) {
        this.customerService = customerService;
        this.produceBoxService = produceBoxService;
        this.subscriptionService = subscriptionService;
        this.reviewService = reviewService;
    }

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer/signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute Customer customer) {
        customerService.createCustomer(customer);
        return "redirect:/signin";
    }

    @PostMapping("/signin")
    public String signin(@RequestParam String email, @RequestParam String password, HttpSession session) {
        try {
            Customer customer = customerService.authenticate(email, password);
            session.setAttribute("customerId", customer.getId());
            return "redirect:/customers/dashboard";
        } catch (Exception e) {
            return "redirect:/signin?error";
        }
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        Long customerId = (Long) session.getAttribute("customerId");
        if (customerId == null) {
            return "redirect:/signin";
        }
        Customer customer = customerService.getCustomerById(customerId);
        model.addAttribute("customer", customer);
        return "customer/dashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("customerId");
        return "redirect:/";
    }

    @GetMapping("/profile/edit")
    public String editProfileForm(HttpSession session, Model model) {
        Long customerId = (Long) session.getAttribute("customerId");
        if (customerId == null) {
            return "redirect:/signin";
        }

        Customer customer = customerService.getCustomerById(customerId);
        model.addAttribute("customer", customer);
        return "customer/edit-profile";
    }

    @PostMapping("/profile/edit")
    public String updateProfile(@RequestParam String name,
                              @RequestParam String email,
                              @RequestParam String shippingAddress,
                              @RequestParam(required = false) String phoneNumber,
                              @RequestParam String currentPassword,
                              @RequestParam(required = false) String newPassword,
                              HttpSession session,
                              Model model) {
        Long customerId = (Long) session.getAttribute("customerId");
        if (customerId == null) {
            return "redirect:/signin";
        }

        Customer customer = customerService.getCustomerById(customerId);

        try {
            // Verify current password
            customerService.authenticate(customer.getEmail(), currentPassword);

            // Update customer information
            Customer updatedCustomer = new Customer();
            updatedCustomer.setName(name);
            updatedCustomer.setEmail(email);
            updatedCustomer.setShippingAddress(shippingAddress);
            updatedCustomer.setPhoneNumber(phoneNumber);
            updatedCustomer.setPassword(newPassword != null && !newPassword.trim().isEmpty()
                ? newPassword : customer.getPassword());

            customerService.updateCustomer(customerId, updatedCustomer);

            return "redirect:/customers/dashboard";
        } catch (Exception e) {
            // If password verification fails or other error occurs
            Customer originalCustomer = customerService.getCustomerById(customerId);
            model.addAttribute("customer", originalCustomer);
            model.addAttribute("error", "Invalid current password");
            return "customer/edit-profile";
        }
    }

    @GetMapping("/boxes")
    public String browseBoxes(Model model) {
        List<ProduceBox> availableBoxes = produceBoxService.getAvailableProduceBoxes();
        model.addAttribute("boxes", availableBoxes);
        return "customer/boxes";
    }

    @GetMapping("/boxes/{id}")
    public String boxDetails(@PathVariable Long id, Model model, HttpSession session) {
        Long customerId = (Long) session.getAttribute("customerId");
        if (customerId == null) {
            return "redirect:/signin";
        }

        ProduceBox box = produceBoxService.getProduceBoxById(id);
        model.addAttribute("box", box);
        return "customer/box-details";
    }

    @PostMapping("/boxes/{id}/subscribe")
    public String subscribe(@PathVariable Long id, @RequestParam SubscriptionType subscriptionType, HttpSession session) {
        Long customerId = (Long) session.getAttribute("customerId");
        if (customerId == null) {
            return "redirect:/signin";
        }

        Customer customer = customerService.getCustomerById(customerId);
        ProduceBox box = produceBoxService.getProduceBoxById(id);

        Subscription subscription = new Subscription();
        subscription.setCustomer(customer);
        subscription.setProduceBox(box);
        subscription.setType(subscriptionType);
        subscription.setStartDate(LocalDateTime.now());
        subscription.setActive(true);
        subscriptionService.createSubscription(subscription);

        return "redirect:/customers/dashboard";
    }

    @GetMapping("/boxes/{boxId}/review")
    public String reviewForm(@PathVariable Long boxId, HttpSession session, Model model) {
        Long customerId = (Long) session.getAttribute("customerId");
        if (customerId == null) {
            return "redirect:/signin";
        }

        Customer customer = customerService.getCustomerById(customerId);
        ProduceBox box = produceBoxService.getProduceBoxById(boxId);

        // Check if customer has an active subscription for this box
        boolean hasSubscription = customer.getSubscriptions().stream()
            .anyMatch(sub -> sub.getProduceBox().getId().equals(boxId));

        if (!hasSubscription) {
            return "redirect:/customers/dashboard";
        }

        Review review = new Review();
        review.setCustomer(customer);
        review.setProduceBox(box);
        model.addAttribute("review", review);
        model.addAttribute("box", box);

        return "customer/review-form";
    }

    @PostMapping("/boxes/{boxId}/review")
    public String submitReview(@PathVariable Long boxId,
                             @ModelAttribute Review review,
                             @RequestParam Double freshnessRating,
                             @RequestParam Double deliveryRating,
                             @RequestParam(required = false) String comment,
                             HttpSession session) {
        Long customerId = (Long) session.getAttribute("customerId");
        if (customerId == null) {
            return "redirect:/signin";
        }

        Customer customer = customerService.getCustomerById(customerId);
        ProduceBox box = produceBoxService.getProduceBoxById(boxId);

        review.setCustomer(customer);
        review.setProduceBox(box);
        review.setFreshnessRating(freshnessRating);
        review.setDeliveryRating(deliveryRating);
        review.setOverallRating((freshnessRating + deliveryRating) / 2);
        review.setComment(comment);
        review.setCreatedAt(LocalDateTime.now());

        reviewService.createReview(review);

        return "redirect:/customers/dashboard";
    }
}