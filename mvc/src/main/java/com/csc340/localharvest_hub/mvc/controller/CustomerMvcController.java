package com.csc340.localharvest_hub.mvc.controller;
import com.csc340.localharvest_hub.mvc.dto.CustomerDTO;
import com.csc340.localharvest_hub.mvc.service.CustomerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customers")
public class CustomerMvcController {
    private final CustomerService customerService;

    public CustomerMvcController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("customer", new CustomerDTO());
        return "customer/signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute CustomerDTO customer) {
        customerService.createCustomer(customer);
        return "redirect:/signin";
    }

    @PostMapping("/signin")
    public String signin(@RequestParam String email, @RequestParam String password, HttpSession session) {
        try {
            CustomerDTO customer = customerService.authenticate(email, password);
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
        CustomerDTO customer = customerService.getCustomer(customerId);
        model.addAttribute("customer", customer);
        return "customer/dashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("customerId");
        return "redirect:/";
    }
}