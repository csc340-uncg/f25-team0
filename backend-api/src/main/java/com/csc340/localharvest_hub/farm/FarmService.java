package com.csc340.localharvest_hub.farm;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class FarmService {
    private final FarmRepository farmRepository;

    public Farm createFarm(Farm farm) {
        if (farmRepository.existsByFarmName(farm.getFarmName())) {
            throw new IllegalStateException("Farm name already exists");
        }
        return farmRepository.save(farm);
    }

    public Farm updateFarm(Long id, Farm farmDetails) {
        Farm farm = farmRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Farm not found"));

        if (!farm.getFarmName().equals(farmDetails.getFarmName()) &&
                farmRepository.existsByFarmName(farmDetails.getFarmName())) {
            throw new IllegalStateException("Farm name already exists");
        }

        farm.setFarmName(farmDetails.getFarmName());
        farm.setDescription(farmDetails.getDescription());
        farm.setLocation(farmDetails.getLocation());

        return farmRepository.save(farm);
    }

    public void deleteFarm(Long id) {
        if (!farmRepository.existsById(id)) {
            throw new EntityNotFoundException("Farm not found");
        }
        farmRepository.deleteById(id);
    }

    public Farm getFarmById(Long id) {
        return farmRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Farm not found"));
    }

    public List<Farm> getAllFarms() {
        return farmRepository.findAll();
    }
}