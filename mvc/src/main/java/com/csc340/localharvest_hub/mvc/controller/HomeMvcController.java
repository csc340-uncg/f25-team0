package com.csc340.localharvest_hub.mvc.controller;

import com.csc340.localharvest_hub.farm.Farm;
import com.csc340.localharvest_hub.farm.FarmService;
import com.csc340.localharvest_hub.producebox.ProduceBox;
import com.csc340.localharvest_hub.producebox.ProduceBoxService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeMvcController {
    private final FarmService farmService;
    private final ProduceBoxService produceBoxService;

    public HomeMvcController(FarmService farmService, ProduceBoxService produceBoxService) {
        this.farmService = farmService;
        this.produceBoxService = produceBoxService;
    }
    @GetMapping("/")
    public String home(Model model) {
        List<Farm> featuredFarms = farmService.getAllFarms().stream()
                .limit(3)
                .toList();
        List<ProduceBox> featuredBoxes = produceBoxService.getAvailableProduceBoxes().stream()
                .limit(3)
                .toList();

        model.addAttribute("farms", featuredFarms);
        model.addAttribute("boxes", featuredBoxes);
        return "home";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @GetMapping("/signin")
    public String signin() {
        return "signin";
    }
}