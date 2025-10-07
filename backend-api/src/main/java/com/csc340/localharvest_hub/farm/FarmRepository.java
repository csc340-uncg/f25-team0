package com.csc340.localharvest_hub.farm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csc340.localharvest_hub.farmer.Farmer;

import java.util.Optional;

@Repository
public interface FarmRepository extends JpaRepository<Farm, Long> {
    Optional<Farm> findByFarmer(Farmer farmer);

    boolean existsByFarmName(String farmName);
}