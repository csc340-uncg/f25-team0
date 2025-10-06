package com.csc340.localharvest_hub.review;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

import com.csc340.localharvest_hub.producebox.ProduceBox;
import com.csc340.localharvest_hub.user.User;

@Data
@NoArgsConstructor
@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User customer;

    @ManyToOne
    @JoinColumn(name = "produce_box_id", nullable = false)
    private ProduceBox produceBox;

    @NotNull
    @Min(1)
    @Max(5)
    private Integer freshnessRating;

    @NotNull
    @Min(1)
    @Max(5)
    private Integer deliveryRating;

    @NotNull
    @Min(1)
    @Max(5)
    private Integer overallRating;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @PrePersist
    @PreUpdate
    private void calculateOverallRating() {
        this.overallRating = Math.round((float)(freshnessRating + deliveryRating) / 2);
    }

    @NotNull
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(columnDefinition = "TEXT")
    private String farmerResponse;

    private LocalDateTime farmerResponseDate;
}