package com.csc340.localharvest_hub.mvc.controller;

import com.csc340.localharvest_hub.farmer.Farmer;
import com.csc340.localharvest_hub.farmer.FarmerService;
import com.csc340.localharvest_hub.farm.Farm;
import com.csc340.localharvest_hub.farm.FarmService;
import com.csc340.localharvest_hub.farm.FarmStatistics;
import com.csc340.localharvest_hub.farm.FarmStatisticsService;
import com.csc340.localharvest_hub.producebox.ProduceBox;
import com.csc340.localharvest_hub.producebox.ProduceBoxService;
import com.csc340.localharvest_hub.review.Review;
import com.csc340.localharvest_hub.review.ReviewService;

import java.util.List;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@RequestMapping("/farmers")
public class FarmerMvcController {
    private final FarmerService farmerService;
    private final ProduceBoxService produceBoxService;
    private final FarmService farmService;
    private final FarmStatisticsService farmStatisticsService;
    private final ReviewService reviewService;

    public FarmerMvcController(FarmerService farmerService,
                             ProduceBoxService produceBoxService,
                             FarmService farmService,
                             FarmStatisticsService farmStatisticsService,
                             ReviewService reviewService) {
        this.farmerService = farmerService;
        this.produceBoxService = produceBoxService;
        this.farmService = farmService;
        this.farmStatisticsService = farmStatisticsService;
        this.reviewService = reviewService;
    }

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("farmer", new Farmer());
        return "farmer/signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute Farmer farmer) {
        farmerService.createFarmer(farmer);
        return "redirect:/signin";
    }

    @PostMapping("/signin")
    public String signin(@RequestParam String email, @RequestParam String password, HttpSession session) {
        try {
            Farmer farmer = farmerService.authenticate(email, password);
            session.setAttribute("farmerId", farmer.getId());
            return "redirect:/farmers/dashboard";
        } catch (Exception e) {
            return "redirect:/signin?error";
        }
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        Long farmerId = (Long) session.getAttribute("farmerId");
        if (farmerId == null) {
            return "redirect:/signin";
        }
        Farmer farmer = farmerService.getFarmerById(farmerId);
        model.addAttribute("farmer", farmer);
        return "farmer/dashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("farmerId");
        return "redirect:/";
    }

    @GetMapping("/farm/setup")
    public String setupFarmForm(HttpSession session, Model model) {
        Long farmerId = (Long) session.getAttribute("farmerId");
        if (farmerId == null) {
            return "redirect:/signin";
        }

        Farmer farmer = farmerService.getFarmerById(farmerId);
        if (farmer.getFarm() != null) {
            return "redirect:/farmers/dashboard";
        }

        return "farmer/setup-farm";
    }

    @PostMapping("/farm/setup")
    public String setupFarm(@RequestParam String farmName,
                          @RequestParam String location,
                          @RequestParam(required = false) String description,
                          HttpSession session) {
        Long farmerId = (Long) session.getAttribute("farmerId");
        if (farmerId == null) {
            return "redirect:/signin";
        }

        Farmer farmer = farmerService.getFarmerById(farmerId);
        if (farmer.getFarm() != null) {
            return "redirect:/farmers/dashboard";
        }

        Farm farm = new Farm();
        farm.setFarmName(farmName);
        farm.setLocation(location);
        farm.setDescription(description);
        farm.setFarmer(farmer);

        farmService.createFarm(farm);

        return "redirect:/farmers/dashboard";
    }

    @GetMapping("/profile/edit")
    public String editProfileForm(HttpSession session, Model model) {
        Long farmerId = (Long) session.getAttribute("farmerId");
        if (farmerId == null) {
            return "redirect:/signin";
        }

        Farmer farmer = farmerService.getFarmerById(farmerId);
        model.addAttribute("farmer", farmer);
        return "farmer/edit-profile";
    }

    @PostMapping("/profile/edit")
    public String updateProfile(@RequestParam String name,
                              @RequestParam String email,
                              @RequestParam(required = false) String phoneNumber,
                              @RequestParam String currentPassword,
                              @RequestParam(required = false) String newPassword,
                              HttpSession session,
                              Model model) {
        Long farmerId = (Long) session.getAttribute("farmerId");
        if (farmerId == null) {
            return "redirect:/signin";
        }

        try {
            Farmer farmer = farmerService.getFarmerById(farmerId);
            farmerService.authenticate(farmer.getEmail(), currentPassword);

            Farmer updatedFarmer = new Farmer();
            updatedFarmer.setName(name);
            updatedFarmer.setEmail(email);
            updatedFarmer.setPhoneNumber(phoneNumber);
            updatedFarmer.setPassword(newPassword != null && !newPassword.trim().isEmpty()
                ? newPassword : farmer.getPassword());

            farmerService.updateFarmer(farmerId, updatedFarmer);
            return "redirect:/farmers/dashboard";
        } catch (Exception e) {
            model.addAttribute("farmer", farmerService.getFarmerById(farmerId));
            model.addAttribute("error", "Invalid current password");
            return "farmer/edit-profile";
        }
    }

    @GetMapping("/farm/edit")
    public String editFarmForm(HttpSession session, Model model) {
        Long farmerId = (Long) session.getAttribute("farmerId");
        if (farmerId == null) {
            return "redirect:/signin";
        }

        Farmer farmer = farmerService.getFarmerById(farmerId);
        if (farmer.getFarm() == null) {
            return "redirect:/farmers/dashboard";
        }

        model.addAttribute("farmer", farmer);
        return "farmer/edit-farm";
    }

    @PostMapping("/farm/edit")
    public String updateFarm(@RequestParam String farmName,
                           @RequestParam String location,
                           @RequestParam(required = false) String description,
                           HttpSession session) {
        Long farmerId = (Long) session.getAttribute("farmerId");
        if (farmerId == null) {
            return "redirect:/signin";
        }

        Farmer farmer = farmerService.getFarmerById(farmerId);
        if (farmer.getFarm() == null) {
            return "redirect:/farmers/dashboard";
        }

        Farm farm = farmer.getFarm();
        farm.setFarmName(farmName);
        farm.setLocation(location);
        farm.setDescription(description);
        farmService.updateFarm(farm.getId(), farm);

        return "redirect:/farmers/dashboard";
    }

    @GetMapping("/boxes/{id}/edit")
    public String editBoxForm(@PathVariable Long id, HttpSession session, Model model) {
        Long farmerId = (Long) session.getAttribute("farmerId");
        if (farmerId == null) {
            return "redirect:/signin";
        }

        Farmer farmer = farmerService.getFarmerById(farmerId);
        ProduceBox box = produceBoxService.getProduceBoxById(id);

        // Verify that this box belongs to the farmer
        if (box.getFarm().getId() != farmer.getFarm().getId()) {
            return "redirect:/farmers/dashboard";
        }

        model.addAttribute("box", box);
        return "farmer/edit-box";
    }

    @PostMapping("/boxes/{id}/edit")
    public String updateBox(@PathVariable Long id,
                          @RequestParam String name,
                          @RequestParam(required = false) String description,
                          @RequestParam BigDecimal price,
                          @RequestParam(defaultValue = "false") boolean available,
                          HttpSession session) {
        Long farmerId = (Long) session.getAttribute("farmerId");
        if (farmerId == null) {
            return "redirect:/signin";
        }

        Farmer farmer = farmerService.getFarmerById(farmerId);
        ProduceBox existingBox = produceBoxService.getProduceBoxById(id);

        // Verify that this box belongs to the farmer
        if (existingBox.getFarm().getId() != farmer.getFarm().getId()) {
            return "redirect:/farmers/dashboard";
        }

        ProduceBox box = new ProduceBox();
        box.setName(name);
        box.setDescription(description);
        box.setPrice(price);
        box.setAvailable(available);
        box.setFarm(farmer.getFarm());

        produceBoxService.updateProduceBox(id, box);
        return "redirect:/farmers/dashboard";
    }

    @GetMapping("/reviews")
    public String viewReviews(HttpSession session, Model model) {
        Long farmerId = (Long) session.getAttribute("farmerId");
        if (farmerId == null) {
            return "redirect:/signin";
        }

        Farmer farmer = farmerService.getFarmerById(farmerId);
        if (farmer.getFarm() == null) {
            return "redirect:/farmers/dashboard";
        }

        List<Review> reviews = reviewService.getReviewsByFarmer(farmerService.getFarmerById(farmerId));
        model.addAttribute("reviews", reviews);
        return "farmer/reviews";
    }

    @PostMapping("/reviews/{reviewId}/reply")
    public String replyToReview(@PathVariable Long reviewId,
                              @RequestParam String response,
                              HttpSession session) {
        Long farmerId = (Long) session.getAttribute("farmerId");
        if (farmerId == null) {
            return "redirect:/signin";
        }

        Farmer farmer = farmerService.getFarmerById(farmerId);
        Review review = reviewService.addFarmerResponse(reviewId, response);

        // Verify that this review belongs to one of the farmer's boxes
        if (review.getProduceBox().getFarm().getId() != farmer.getFarm().getId()) {
            return "redirect:/farmers/reviews";
        }

        reviewService.addFarmerResponse(reviewId, response);
        return "redirect:/farmers/reviews";
    }

    @GetMapping("/statistics")
    public String viewStatistics(HttpSession session, Model model) {
        Long farmerId = (Long) session.getAttribute("farmerId");
        if (farmerId == null) {
            return "redirect:/signin";
        }

        FarmStatistics stats = farmStatisticsService.getFarmStatistics(farmerId);
        model.addAttribute("stats", stats);
        return "farmer/statistics";
    }

    @GetMapping("/subscriptions")
    public String viewSubscriptions(HttpSession session, Model model) {
        Long farmerId = (Long) session.getAttribute("farmerId");
        if (farmerId == null) {
            return "redirect:/signin";
        }

        Farmer farmer = farmerService.getFarmerById(farmerId);
        if (farmer.getFarm() == null) {
            return "redirect:/farmers/dashboard";
        }

        model.addAttribute("boxes", farmer.getFarm().getProduceBoxes());
        return "farmer/subscriptions";
    }

    @GetMapping("/boxes/new")
    public String newBoxForm(HttpSession session, Model model) {
        Long farmerId = (Long) session.getAttribute("farmerId");
        if (farmerId == null) {
            return "redirect:/signin";
        }

        Farmer farmer = farmerService.getFarmerById(farmerId);
        if (farmer.getFarm() == null) {
            return "redirect:/farmers/dashboard";
        }

        model.addAttribute("farmer", farmer);
        return "farmer/new-box";
    }

    @PostMapping("/boxes/new")
    public String createBox(@RequestParam String name,
                          @RequestParam(required = false) String description,
                          @RequestParam BigDecimal price,
                          @RequestParam(defaultValue = "true") boolean available,
                          HttpSession session) {
        Long farmerId = (Long) session.getAttribute("farmerId");
        if (farmerId == null) {
            return "redirect:/signin";
        }

        Farmer farmer = farmerService.getFarmerById(farmerId);
        Farm farm = farmer.getFarm();
        if (farm == null) {
            return "redirect:/farmers/dashboard";
        }

        ProduceBox box = new ProduceBox();
        box.setName(name);
        box.setDescription(description);
        box.setPrice(price);
        box.setAvailable(available);
        box.setFarm(farm);

        produceBoxService.createProduceBox(box);

        return "redirect:/farmers/dashboard";
    }
}