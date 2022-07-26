package com.ninjaone.backendinterviewproject.customer.application.service;

import com.ninjaone.backendinterviewproject.customer.domain.Customer;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public interface CustomerService {

    Customer createCustomer(Customer customer);

    Long getTotalMonthlyCost(UUID customerId);

    Optional<Customer> getCustomerById(UUID customerId);

    void updateCustomer(Customer customer);
}
