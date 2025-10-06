package com.csc340.localharvest_hub.review;

import com.csc340.localharvest_hub.producebox.ProduceBox;
import com.csc340.localharvest_hub.user.User;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.OptionalDouble;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {
    private final ReviewRepository reviewRepository;
    
    public double getAverageOverallRating(ProduceBox produceBox) {
        List<Review> reviews = reviewRepository.findByProduceBox(produceBox);
        OptionalDouble average = reviews.stream()
            .mapToInt(Review::getOverallRating)
            .average();
        return average.orElse(0.0);
    }

    public double getAverageFreshnessRating(ProduceBox produceBox) {
        List<Review> reviews = reviewRepository.findByProduceBox(produceBox);
        OptionalDouble average = reviews.stream()
            .mapToInt(Review::getFreshnessRating)
            .average();
        return average.orElse(0.0);
    }

    public double getAverageDeliveryRating(ProduceBox produceBox) {
        List<Review> reviews = reviewRepository.findByProduceBox(produceBox);
        OptionalDouble average = reviews.stream()
            .mapToInt(Review::getDeliveryRating)
            .average();
        return average.orElse(0.0);
    }

    public Review createReview(Review review) {
        review.setCreatedAt(LocalDateTime.now());
        return reviewRepository.save(review);
    }

    public Review addFarmerResponse(Long id, String response) {
        Review review = reviewRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Review not found"));
        
        review.setFarmerResponse(response);
        review.setFarmerResponseDate(LocalDateTime.now());
        return reviewRepository.save(review);
    }

    public void deleteReview(Long id) {
        if (!reviewRepository.existsById(id)) {
            throw new EntityNotFoundException("Review not found");
        }
        reviewRepository.deleteById(id);
    }

    public List<Review> getReviewsByProduceBox(ProduceBox produceBox) {
        return reviewRepository.findByProduceBox(produceBox);
    }

    public List<Review> getReviewsByCustomer(User customer) {
        return reviewRepository.findByCustomer(customer);
    }

    public List<Review> getReviewsByFarmer(User farmer) {
        return reviewRepository.findByProduceBoxFarmFarmer(farmer);
    }
}