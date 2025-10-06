package com.csc340.localharvest_hub.farm;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import com.csc340.localharvest_hub.producebox.ProduceBox;
import com.csc340.localharvest_hub.user.User;

@Data
@NoArgsConstructor
@Entity
@Table(name = "farms")
public class Farm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User farmer;

    @NotBlank
    @Column(nullable = false)
    private String farmName;

    @Column(columnDefinition = "TEXT")
    private String description;

    @NotBlank
    private String location;

    @OneToMany(mappedBy = "farm", cascade = CascadeType.ALL)
    private List<ProduceBox> produceBoxes = new ArrayList<>();
}