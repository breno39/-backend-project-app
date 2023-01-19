package com.breno39.backendinterviewproject.customer.application.service;

import com.breno39.backendinterviewproject.customer.domain.Customer;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface CustomerService {

    Customer createCustomer(Customer customer);

    Long getTotalMonthlyCost(UUID customerId);

    Customer getCustomerById(UUID customerId);

    void updateCustomer(Customer customer);
}
