package com.csc340.localharvest_hub.review;

import com.csc340.localharvest_hub.customer.Customer;
import com.csc340.localharvest_hub.farmer.Farmer;
import com.csc340.localharvest_hub.producebox.ProduceBox;

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
                .mapToDouble(review -> review.getOverallRating() != null ? review.getOverallRating() : 0.0)
                .average();
        return average.orElse(0.0);
    }

    public double getAverageFreshnessRating(ProduceBox produceBox) {
        List<Review> reviews = reviewRepository.findByProduceBox(produceBox);
        OptionalDouble average = reviews.stream()
                .mapToDouble(review -> review.getFreshnessRating() != null ? review.getFreshnessRating() : 0.0)
                .average();
        return average.orElse(0.0);
    }

    public double getAverageDeliveryRating(ProduceBox produceBox) {
        List<Review> reviews = reviewRepository.findByProduceBox(produceBox);
        OptionalDouble average = reviews.stream()
                .mapToDouble(review -> review.getDeliveryRating() != null ? review.getDeliveryRating() : 0.0)
                .average();
        return average.orElse(0.0);
    }

    public Review createReview(Review review) {
        double freshnessRating = review.getFreshnessRating() != null ? review.getFreshnessRating() : 0;
        double deliveryRating = review.getDeliveryRating() != null ? review.getDeliveryRating() : 0;

        review.setOverallRating(Double.valueOf(freshnessRating + deliveryRating) / 2);
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

    public List<Review> getReviewsByCustomer(Customer customer) {
        return reviewRepository.findByCustomer(customer);
    }

    public List<Review> getReviewsByFarmer(Farmer farmer) {
        return reviewRepository.findByProduceBoxFarmFarmer(farmer);
    }
}