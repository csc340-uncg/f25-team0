package com.csc340.localharvest_hub.farmer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/farmers")
@RequiredArgsConstructor
public class FarmerController {
    private final FarmerService farmerService;

    @PostMapping
    public ResponseEntity<Farmer> createFarmer(@Valid @RequestBody Farmer farmer) {
        return ResponseEntity.ok(farmerService.createFarmer(farmer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Farmer> updateFarmer(@PathVariable Long id, @Valid @RequestBody Farmer farmerDetails) {
        return ResponseEntity.ok(farmerService.updateFarmer(id, farmerDetails));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Farmer> getFarmer(@PathVariable Long id) {
        return ResponseEntity.ok(farmerService.getFarmerById(id));
    }
}