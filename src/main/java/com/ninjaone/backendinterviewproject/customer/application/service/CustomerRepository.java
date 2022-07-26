package com.ninjaone.backendinterviewproject.customer.application.service;

import com.ninjaone.backendinterviewproject.customer.domain.Customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {
    Customer createCustomer(Customer customer);

    Optional<Long> findTotalMonthlyCostById(UUID customerId);
}
