package com.csc340.localharvest_hub.producebox;

import com.csc340.localharvest_hub.farm.FarmService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boxes")
@RequiredArgsConstructor
public class ProduceBoxController {
    private final ProduceBoxService produceBoxService;
    private final FarmService farmService;

    @PostMapping
    public ResponseEntity<ProduceBox> createProduceBox(@Valid @RequestBody ProduceBox box) {
        return ResponseEntity.ok(produceBoxService.createProduceBox(box));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProduceBox> updateProduceBox(@PathVariable Long id, @Valid @RequestBody ProduceBox boxDetails) {
        return ResponseEntity.ok(produceBoxService.updateProduceBox(id, boxDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduceBox(@PathVariable Long id) {
        produceBoxService.deleteProduceBox(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProduceBox> getProduceBox(@PathVariable Long id) {
        return ResponseEntity.ok(produceBoxService.getProduceBoxById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProduceBox>> getAvailableProduceBoxes() {
        return ResponseEntity.ok(produceBoxService.getAvailableProduceBoxes());
    }

    @GetMapping("/farm/{farmId}")
    public ResponseEntity<List<ProduceBox>> getProduceBoxesByFarm(@PathVariable Long farmId) {
        return ResponseEntity.ok(produceBoxService.getProduceBoxesByFarm(farmService.getFarmById(farmId)));
    }
}