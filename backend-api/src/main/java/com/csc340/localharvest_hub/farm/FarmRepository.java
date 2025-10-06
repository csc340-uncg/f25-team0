package com.csc340.localharvest_hub.farm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csc340.localharvest_hub.user.User;

import java.util.Optional;

@Repository
public interface FarmRepository extends JpaRepository<Farm, Long> {
    Optional<Farm> findByFarmer(User farmer);

    boolean existsByFarmName(String farmName);
}