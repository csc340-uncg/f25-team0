package com.csc340.localharvest_hub.subscription;

import com.csc340.localharvest_hub.producebox.ProduceBox;
import com.csc340.localharvest_hub.user.User;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByCustomerAndActive(User customer, boolean active);
    List<Subscription> findByProduceBox(ProduceBox produceBox);
    List<Subscription> findByProduceBoxFarmFarmer(User farmer);
}