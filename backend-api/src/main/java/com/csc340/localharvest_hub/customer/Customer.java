package com.csc340.localharvest_hub.customer;

import com.csc340.localharvest_hub.subscription.Subscription;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @Email
    @NotBlank
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "customer")
    @JsonManagedReference
    private List<Subscription> subscriptions = new ArrayList<>();

    @NotBlank
    private String shippingAddress;

    private String phoneNumber;

    public Customer(Long id) {
        this.id = id;
    }

}