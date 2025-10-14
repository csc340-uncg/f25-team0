package com.csc340.localharvest_hub.farm;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

import com.csc340.localharvest_hub.farmer.Farmer;
import com.csc340.localharvest_hub.producebox.ProduceBox;

@Data
@NoArgsConstructor
@Entity
@Table(name = "farms")
public class Farm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "farmer_id", nullable = false)
    @JsonIgnoreProperties("farm")
    private Farmer farmer;

    @NotBlank
    @Column(nullable = false)
    private String farmName;

    @Column(columnDefinition = "TEXT")
    private String description;

    @NotBlank
    private String location;

    @OneToMany(mappedBy = "farm", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("farm")
    private List<ProduceBox> produceBoxes = new ArrayList<>();
}