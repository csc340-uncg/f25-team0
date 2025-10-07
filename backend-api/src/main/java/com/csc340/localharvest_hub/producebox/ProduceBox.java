package com.csc340.localharvest_hub.producebox;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.csc340.localharvest_hub.farm.Farm;
import com.csc340.localharvest_hub.review.Review;
import com.csc340.localharvest_hub.subscription.Subscription;

@Data
@NoArgsConstructor
@Entity
@Table(name = "produce_boxes")
public class ProduceBox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "farm_id", nullable = false)
    @JsonIgnoreProperties("produceBoxes")
    private Farm farm;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull
    @Positive
    private BigDecimal price;

    @NotNull
    private boolean available = true;

    @OneToMany(mappedBy = "produceBox", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("produceBox")
    private List<Subscription> subscriptions = new ArrayList<>();

    @OneToMany(mappedBy = "produceBox", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("produceBox")
    private List<Review> reviews = new ArrayList<>();
}