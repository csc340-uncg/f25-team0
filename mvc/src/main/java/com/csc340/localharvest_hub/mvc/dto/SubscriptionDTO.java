package com.csc340.localharvest_hub.mvc.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SubscriptionDTO {
    private Long id;
    private Long customerId;
    private Long produceBoxId;
    private String type;
    private String startDate;
    private String endDate;
    private boolean active;
}