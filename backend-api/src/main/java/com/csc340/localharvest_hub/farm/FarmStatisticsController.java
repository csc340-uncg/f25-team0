package com.csc340.localharvest_hub.farm;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/farms/statistics")
@RequiredArgsConstructor
public class FarmStatisticsController {
    private final FarmStatisticsService farmStatisticsService;

    @GetMapping("/{farmerId}")
    public ResponseEntity<FarmStatistics> getFarmStatistics(@PathVariable Long farmerId) {
        return ResponseEntity.ok(farmStatisticsService.getFarmStatistics(farmerId));
    }
}