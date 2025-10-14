package com.csc340.localharvest_hub.review;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

import com.csc340.localharvest_hub.customer.Customer;
import com.csc340.localharvest_hub.producebox.ProduceBox;

@Data
@NoArgsConstructor
@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnoreProperties({"reviews", "subscriptions"})
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "produce_box_id", nullable = false)
    @JsonIgnoreProperties("reviews")
    private ProduceBox produceBox;

    @NotNull
    @Min(1)
    @Max(5)
    private Double freshnessRating;

    @NotNull
    @Min(1)
    @Max(5)
    private Double deliveryRating;

    @Min(1)
    @Max(5)
    private Double overallRating;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @NotNull
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(columnDefinition = "TEXT")
    private String farmerResponse;

    private LocalDateTime farmerResponseDate;
}