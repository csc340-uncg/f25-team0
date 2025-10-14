package com.csc340.localharvest_hub.subscription;

import com.csc340.localharvest_hub.customer.Customer;
import com.csc340.localharvest_hub.farmer.Farmer;
import com.csc340.localharvest_hub.producebox.ProduceBox;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByCustomerAndActive(Customer customer, boolean active);
    List<Subscription> findByProduceBox(ProduceBox produceBox);
    List<Subscription> findByProduceBoxFarmFarmer(Farmer farmer);
}