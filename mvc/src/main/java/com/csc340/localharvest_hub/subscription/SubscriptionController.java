package com.csc340.localharvest_hub.subscription;

import com.csc340.localharvest_hub.customer.CustomerService;
import com.csc340.localharvest_hub.farmer.FarmerService;
import com.csc340.localharvest_hub.producebox.ProduceBoxService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;
    private final ProduceBoxService produceBoxService;
    private final CustomerService customerService;
    private final FarmerService farmerService;

    @PostMapping
    public ResponseEntity<Subscription> createSubscription(@Valid @RequestBody Subscription subscription) {
        return ResponseEntity.ok(subscriptionService.createSubscription(subscription));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subscription> updateSubscription(@PathVariable Long id, @Valid @RequestBody Subscription subscriptionDetails) {
        return ResponseEntity.ok(subscriptionService.updateSubscription(id, subscriptionDetails));
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<Void> cancelSubscription(@PathVariable Long id) {
        subscriptionService.cancelSubscription(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Subscription>> getCustomerSubscriptions(@PathVariable Long customerId) {
        return ResponseEntity.ok(subscriptionService.getActiveSubscriptionsByCustomer(customerService.getCustomerById(customerId)));
    }

    @GetMapping("/box/{boxId}")
    public ResponseEntity<List<Subscription>> getBoxSubscriptions(@PathVariable Long boxId) {
        return ResponseEntity.ok(subscriptionService.getSubscriptionsByProduceBox(produceBoxService.getProduceBoxById(boxId)));
    }

    @GetMapping("/farmer/{farmerId}")
    public ResponseEntity<List<Subscription>> getFarmerSubscriptions(@PathVariable Long farmerId) {
        return ResponseEntity.ok(subscriptionService.getSubscriptionsByFarmer(farmerService.getFarmerById(farmerId)));
    }
}