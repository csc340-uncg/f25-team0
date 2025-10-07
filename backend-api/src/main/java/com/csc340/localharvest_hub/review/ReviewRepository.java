package com.csc340.localharvest_hub.review;

import com.csc340.localharvest_hub.customer.Customer;
import com.csc340.localharvest_hub.farmer.Farmer;
import com.csc340.localharvest_hub.producebox.ProduceBox;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByProduceBox(ProduceBox produceBox);
    List<Review> findByCustomer(Customer customer);
    List<Review> findByProduceBoxFarmFarmer(Farmer farmer);
}