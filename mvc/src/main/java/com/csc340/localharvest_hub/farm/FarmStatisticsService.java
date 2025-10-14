package com.csc340.localharvest_hub.farm;

import com.csc340.localharvest_hub.subscription.SubscriptionRepository;

import com.csc340.localharvest_hub.subscription.Subscription;

import com.csc340.localharvest_hub.review.ReviewRepository;
import com.csc340.localharvest_hub.farmer.FarmerService;
import com.csc340.localharvest_hub.producebox.ProduceBox;
import com.csc340.localharvest_hub.producebox.ProduceBoxRepository;
import com.csc340.localharvest_hub.review.Review;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FarmStatisticsService {
    private final FarmRepository farmRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final ProduceBoxRepository produceBoxRepository;
    private final ReviewRepository reviewRepository;
    private final FarmerService farmerService;


    public FarmStatistics getFarmStatistics(Long farmerId) {
        Farm farm = farmRepository.findByFarmer(farmerService.getFarmerById(farmerId))
                .orElseThrow(() -> new EntityNotFoundException("Farm not found"));

        FarmStatistics stats = new FarmStatistics();

        // Calculate revenue statistics
        calculateRevenueStatistics(stats, farm);

        // Calculate subscriber statistics
        calculateSubscriberStatistics(stats, farm);

        // Calculate box statistics
        calculateBoxStatistics(stats, farm);

        // Calculate rating statistics
        calculateRatingStatistics(stats, farm);

        // Calculate review statistics
        calculateReviewStatistics(stats, farm);

        return stats;
    }

    private void calculateRevenueStatistics(FarmStatistics stats, Farm farm) {
        List<Subscription> allSubscriptions = subscriptionRepository.findByProduceBoxFarmFarmer(farm.getFarmer());

        // Total revenue
        BigDecimal total = allSubscriptions.stream()
                .map(sub -> sub.getProduceBox().getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        stats.setTotalRevenue(total);

        // Monthly revenue (current month)
        LocalDateTime startOfMonth = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0);
        BigDecimal monthly = allSubscriptions.stream()
                .filter(sub -> sub.getStartDate().isAfter(startOfMonth))
                .map(sub -> sub.getProduceBox().getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        stats.setMonthlyRevenue(monthly);

        // Revenue by month (last 6 months)
        Map<String, BigDecimal> revenueByMonth = new LinkedHashMap<>();
        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MMMM yyyy");

        for (int i = 5; i >= 0; i--) {
            LocalDateTime monthStart = LocalDateTime.now().minusMonths(i).withDayOfMonth(1).withHour(0).withMinute(0);
            LocalDateTime monthEnd = monthStart.plusMonths(1);

            BigDecimal monthlyRev = allSubscriptions.stream()
                    .filter(sub -> sub.getStartDate().isAfter(monthStart) && sub.getStartDate().isBefore(monthEnd))
                    .map(sub -> sub.getProduceBox().getPrice())
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            revenueByMonth.put(monthStart.format(monthFormatter), monthlyRev);
        }
        stats.setRevenueByMonth(revenueByMonth);
    }

    private void calculateSubscriberStatistics(FarmStatistics stats, Farm farm) {
        List<Subscription> allSubscriptions = subscriptionRepository.findByProduceBoxFarmFarmer(farm.getFarmer());

        // Total and active subscribers
        stats.setTotalSubscribers(allSubscriptions.size());
        stats.setActiveSubscribers(allSubscriptions.stream().filter(Subscription::isActive).count());

        // Subscribers by month
        Map<String, Long> subscribersByMonth = new LinkedHashMap<>();
        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MMMM yyyy");

        for (int i = 5; i >= 0; i--) {
            LocalDateTime monthStart = LocalDateTime.now().minusMonths(i).withDayOfMonth(1).withHour(0).withMinute(0);
            String monthKey = monthStart.format(monthFormatter);

            long subscribersCount = allSubscriptions.stream()
                    .filter(sub -> sub.getStartDate().getMonth() == monthStart.getMonth()
                            && sub.getStartDate().getYear() == monthStart.getYear())
                    .count();

            subscribersByMonth.put(monthKey, subscribersCount);
        }
        stats.setSubscribersByMonth(subscribersByMonth);
    }

    private void calculateBoxStatistics(FarmStatistics stats, Farm farm) {
        List<ProduceBox> boxes = produceBoxRepository.findByFarmAndAvailable(farm, true);
        stats.setTotalBoxes(boxes.size());
        stats.setActiveBoxes((int) boxes.stream().filter(ProduceBox::isAvailable).count());

        // Most popular boxes
        Map<String, Long> popularBoxes = boxes.stream()
                .collect(Collectors.toMap(
                        ProduceBox::getName,
                        box -> subscriptionRepository.findByProduceBox(box).stream()
                                .filter(Subscription::isActive)
                                .count()));
        stats.setMostPopularBoxes(popularBoxes);
    }

    private void calculateRatingStatistics(FarmStatistics stats, Farm farm) {
        List<Review> allReviews = reviewRepository.findByProduceBoxFarmFarmer(farm.getFarmer());

        // Average ratings
        stats.setAverageOverallRating(calculateAverageRating(allReviews, Review::getOverallRating));
        stats.setAverageFreshnessRating(calculateAverageRating(allReviews, Review::getFreshnessRating));
        stats.setAverageDeliveryRating(calculateAverageRating(allReviews, Review::getDeliveryRating));

        // Ratings by box
        Map<String, Double> ratingsByBox = farm.getProduceBoxes().stream()
                .collect(Collectors.toMap(
                        ProduceBox::getName,
                        box -> calculateAverageRating(
                                reviewRepository.findByProduceBox(box),
                                Review::getOverallRating)));
        stats.setRatingsByBox(ratingsByBox);
    }

    private void calculateReviewStatistics(FarmStatistics stats, Farm farm) {
        List<Review> allReviews = reviewRepository.findByProduceBoxFarmFarmer(farm.getFarmer());
        stats.setTotalReviews(allReviews.size());

        // Response rate
        long reviewsWithResponse = allReviews.stream()
                .filter(review -> review.getFarmerResponse() != null)
                .count();
        stats.setResponseRate(allReviews.isEmpty() ? 0.0 : (double) reviewsWithResponse / allReviews.size() * 100);

        // Rating distribution
        Map<Double, Long> distribution = allReviews.stream()
                .collect(Collectors.groupingBy(
                        review -> review.getOverallRating(),
                        Collectors.counting()));
        stats.setRatingDistribution(distribution);
    }

    private double calculateAverageRating(List<Review> reviews,
            java.util.function.Function<Review, Double> ratingExtractor) {
        return reviews.stream()
                .mapToDouble(ratingExtractor::apply)
                .average()
                .orElse(0.0);
    }

}