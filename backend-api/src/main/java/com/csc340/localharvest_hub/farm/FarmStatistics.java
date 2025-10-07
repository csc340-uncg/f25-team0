package com.csc340.localharvest_hub.farm;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Map;

@Data
public class FarmStatistics {
    // Revenue Statistics
    private BigDecimal totalRevenue;
    private BigDecimal monthlyRevenue;
    private Map<String, BigDecimal> revenueByMonth; // Last 6 months

    // Subscriber Statistics
    private long totalSubscribers;
    private long activeSubscribers;
    private Map<String, Long> subscribersByMonth; // Subscription growth trend

    // Box Statistics
    private int totalBoxes;
    private int activeBoxes;
    private Map<String, Long> mostPopularBoxes; // Box name -> subscriber count

    // Rating Statistics
    private double averageOverallRating;
    private double averageFreshnessRating;
    private double averageDeliveryRating;
    private Map<String, Double> ratingsByBox; // Box name -> rating

    // Review Statistics
    private long totalReviews;
    private double responseRate; // Percentage of reviews with farmer responses
    private Map<Double, Long> ratingDistribution; // Rating -> count
}