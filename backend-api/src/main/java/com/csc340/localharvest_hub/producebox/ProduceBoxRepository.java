package com.csc340.localharvest_hub.producebox;

import org.springframework.data.jpa.repository.JpaRepository;

import com.csc340.localharvest_hub.farm.Farm;

import java.util.List;

public interface ProduceBoxRepository extends JpaRepository<ProduceBox, Long> {
    List<ProduceBox> findByFarmAndAvailable(Farm farm, boolean available);
    List<ProduceBox> findByAvailable(boolean available);
}