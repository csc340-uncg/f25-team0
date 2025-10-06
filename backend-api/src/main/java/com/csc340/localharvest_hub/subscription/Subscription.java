package com.csc340.localharvest_hub.subscription;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

import com.csc340.localharvest_hub.producebox.ProduceBox;
import com.csc340.localharvest_hub.user.User;

@Data
@NoArgsConstructor
@Entity
@Table(name = "subscriptions")
public class Subscription {
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