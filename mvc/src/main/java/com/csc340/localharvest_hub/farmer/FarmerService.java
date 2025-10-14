package com.csc340.localharvest_hub.farmer;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class FarmerService {
    private final FarmerRepository farmerRepository;

    public Farmer createFarmer(Farmer farmer) {
        if (farmerRepository.existsByEmail(farmer.getEmail())) {
            throw new IllegalStateException("Email already registered");
        }
        return farmerRepository.save(farmer);
    }

    public Farmer updateFarmer(Long id, Farmer farmerDetails) {
        Farmer farmer = farmerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Farmer not found"));

        farmer.setName(farmerDetails.getName());
        if (!farmer.getEmail().equals(farmerDetails.getEmail()) &&
                farmerRepository.existsByEmail(farmerDetails.getEmail())) {
            throw new IllegalStateException("Email already registered");
        }
        farmer.setEmail(farmerDetails.getEmail());
        farmer.setPhoneNumber(farmerDetails.getPhoneNumber());

        return farmerRepository.save(farmer);
    }

    public Farmer getFarmerById(Long id) {
        return farmerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Farmer not found"));
    }

    public Farmer getFarmerByEmail(String email) {
        return farmerRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Farmer not found"));
    }

    public Farmer authenticate(String email, String password) {
        Farmer farmer = farmerRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        if (!farmer.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid email or password");
        }
        return farmer;
    }
}