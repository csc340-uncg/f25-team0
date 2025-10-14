package com.csc340.localharvest_hub.mvc.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerDTO {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String shippingAddress;
    private String phoneNumber;
}