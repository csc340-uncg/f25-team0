package com.csc340.localharvest_hub.mvc.controller;

import com.csc340.localharvest_hub.mvc.dto.FarmerDTO;
import com.csc340.localharvest_hub.mvc.service.FarmerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/farmers")
public class FarmerMvcController {
    private final FarmerService farmerService;

    public FarmerMvcController(FarmerService farmerService) {
        this.farmerService = farmerService;
    }

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("farmer", new FarmerDTO());
        return "farmer/signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute FarmerDTO farmer) {
        farmerService.createFarmer(farmer);
        return "redirect:/signin";
    }

    @PostMapping("/signin")
    public String signin(@RequestParam String email, @RequestParam String password, HttpSession session) {
        try {
            FarmerDTO farmer = farmerService.authenticate(email, password);
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
        FarmerDTO farmer = farmerService.getFarmer(farmerId);
        model.addAttribute("farmer", farmer);
        return "farmer/dashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("farmerId");
        return "redirect:/";
    }
}