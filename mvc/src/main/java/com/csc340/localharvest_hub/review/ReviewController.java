package com.csc340.localharvest_hub.review;

import com.csc340.localharvest_hub.customer.CustomerService;
import com.csc340.localharvest_hub.farmer.FarmerService;
import com.csc340.localharvest_hub.producebox.ProduceBox;
import com.csc340.localharvest_hub.producebox.ProduceBoxService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final ProduceBoxService produceBoxService;
    private  final CustomerService customerService;
    private final FarmerService farmerService;;

    @PostMapping
    public ResponseEntity<Review> createReview(@Valid @RequestBody Review review) {
        return ResponseEntity.ok(reviewService.createReview(review));
    }

    @PostMapping("/{id}/farmer-response")
    public ResponseEntity<Review> addFarmerResponse(@PathVariable Long id, @RequestBody String response) {
        return ResponseEntity.ok(reviewService.addFarmerResponse(id, response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/box/{boxId}")
    public ResponseEntity<List<Review>> getBoxReviews(@PathVariable Long boxId) {
        return ResponseEntity.ok(reviewService.getReviewsByProduceBox(produceBoxService.getProduceBoxById(boxId)));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Review>> getCustomerReviews(@PathVariable Long customerId) {
        return ResponseEntity.ok(reviewService.getReviewsByCustomer(customerService.getCustomerById(customerId)));
    }

    @GetMapping("/farmer/{farmerId}")
    public ResponseEntity<List<Review>> getFarmerReviews(@PathVariable Long farmerId) {
        return ResponseEntity.ok(reviewService.getReviewsByFarmer(farmerService.getFarmerById(farmerId)));
    }

    @GetMapping("/box/{boxId}/ratings")
    public ResponseEntity<Map<String, Double>> getBoxRatings(@PathVariable Long boxId) {
        ProduceBox box = produceBoxService.getProduceBoxById(boxId);
        Map<String, Double> ratings = new HashMap<>();
        ratings.put("overall", reviewService.getAverageOverallRating(box));
        ratings.put("freshness", reviewService.getAverageFreshnessRating(box));
        ratings.put("delivery", reviewService.getAverageDeliveryRating(box));
        return ResponseEntity.ok(ratings);
    }
}