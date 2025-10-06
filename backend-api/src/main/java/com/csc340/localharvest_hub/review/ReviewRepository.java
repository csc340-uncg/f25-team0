package com.csc340.localharvest_hub.review;

import com.csc340.localharvest_hub.producebox.ProduceBox;
import com.csc340.localharvest_hub.user.User;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByProduceBox(ProduceBox produceBox);
    List<Review> findByCustomer(User customer);
    List<Review> findByProduceBoxFarmFarmer(User farmer);
}