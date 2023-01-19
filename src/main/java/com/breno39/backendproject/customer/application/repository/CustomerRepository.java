package com.breno39.backendproject.customer.application.repository;

import com.breno39.backendproject.customer.domain.Customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {
    Customer createCustomer(Customer customer);

    Optional<Long> findTotalMonthlyCostById(UUID customerId);

    Optional<Customer> findById(UUID customerId);

    void update(Customer customer);
}
