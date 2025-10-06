package com.csc340.localharvest_hub.user;

import com.csc340.localharvest_hub.subscription.Subscription;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Customer extends User {
    @OneToMany(mappedBy = "user")
    private List<Subscription> subscriptions = new ArrayList<>();
    
    private String shippingAddress;
    private String phoneNumber;
    
    @Override
    public void setRole(UserRole role) {
        if (role != UserRole.CUSTOMER) {
            throw new IllegalArgumentException("Customer class can only have CUSTOMER role");
        }
        super.setRole(role);
    }
}