package com.csc340.localharvest_hub.subscription;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.LocalDateTime;

import com.csc340.localharvest_hub.customer.Customer;
import com.csc340.localharvest_hub.producebox.ProduceBox;

@Data
@NoArgsConstructor
@Entity
@Table(name = "subscriptions")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonBackReference(value = "customer-subscriptions")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "produce_box_id", nullable = false)
    @JsonBackReference(value = "producebox-subscriptions")
    private ProduceBox produceBox;

    @NotNull
    @Enumerated(EnumType.STRING)
    private SubscriptionType type;

    @NotNull
    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @NotNull
    private boolean active = true;
}

enum SubscriptionType {
    ONE_TIME,
    MONTHLY
}