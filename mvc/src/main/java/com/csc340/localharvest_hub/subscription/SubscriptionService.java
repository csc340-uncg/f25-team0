package com.csc340.localharvest_hub.subscription;

import com.csc340.localharvest_hub.customer.Customer;
import com.csc340.localharvest_hub.farmer.Farmer;
import com.csc340.localharvest_hub.producebox.ProduceBox;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;

    public Subscription createSubscription(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    public Subscription updateSubscription(Long id, Subscription subscriptionDetails) {
        Subscription subscription = subscriptionRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Subscription not found"));

        subscription.setType(subscriptionDetails.getType());
        subscription.setActive(subscriptionDetails.isActive());
        subscription.setStartDate(subscriptionDetails.getStartDate());
        subscription.setEndDate(subscriptionDetails.getEndDate());

        return subscriptionRepository.save(subscription);
    }

    public void cancelSubscription(Long id) {
        Subscription subscription = subscriptionRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Subscription not found"));
        subscription.setActive(false);
        subscriptionRepository.save(subscription);
    }

    public List<Subscription> getActiveSubscriptionsByCustomer(Customer customer) {
        return subscriptionRepository.findByCustomerAndActive(customer, true);
    }

    public List<Subscription> getSubscriptionsByProduceBox(ProduceBox produceBox) {
        return subscriptionRepository.findByProduceBox(produceBox);
    }

    public List<Subscription> getSubscriptionsByFarmer(Farmer farmer) {
        return subscriptionRepository.findByProduceBoxFarmFarmer(farmer);
    }

    public Subscription recordDelivery(Long id, LocalDateTime deliveryDate) {
        Subscription subscription = subscriptionRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Subscription not found"));

        subscription.setLastDeliveryDate(deliveryDate);
        return subscriptionRepository.save(subscription);
    }
}