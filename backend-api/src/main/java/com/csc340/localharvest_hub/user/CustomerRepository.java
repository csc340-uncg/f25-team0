package com.csc340.localharvest_hub.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByShippingAddressContaining(String address);
    List<Customer> findByPhoneNumberContaining(String phoneNumber);
}