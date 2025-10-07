package com.csc340.localharvest_hub.farmer;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import com.csc340.localharvest_hub.farm.Farm;

@Data
@NoArgsConstructor
@Entity
@Table(name = "farmers")
public class Farmer {
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

    @OneToOne(mappedBy = "farmer", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Farm farm;

    private String phoneNumber;

    public Farmer(Long id) {
        this.id = id;
    }
}