package com.breno39.backendproject.customer.application.service;

import com.breno39.backendproject.customer.application.repository.CustomerRepository;
import com.breno39.backendproject.customer.domain.Customer;
import com.breno39.backendproject.handler.ApiException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerSpringDataJPAService implements CustomerService{
    private Logger logger = LoggerFactory.getLogger(CustomerSpringDataJPAService.class);

    private final CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(Customer customer) {
        logger.info("[START] - CustomerSpringDataJPAService - createCustomer");
        customer.getCredential().encryptPassword();
        try {
            var createdCustomer = customerRepository.createCustomer(customer);
            logger.info("created Customer {}", createdCustomer.getId());
            logger.info("[FINISH] - CustomerSpringDataJPAService - createCustomer");
            return createdCustomer;
        } catch (DataIntegrityViolationException ex) {
            throw ApiException.throwApiException(HttpStatus.BAD_REQUEST, "username already exists");
        }
    }

    @Override
    public Long getTotalMonthlyCost(UUID customerId) {
        logger.info("[START] - CustomerSpringDataJPAService - getTotalMonthlyCost");
        Long totalMonthlyCost = customerRepository.findTotalMonthlyCostById(customerId)
                .orElseThrow(() -> ApiException.throwApiException(HttpStatus.NOT_FOUND, "Customer not found"));
        logger.info("[FINISH] - CustomerSpringDataJPAService - getTotalMonthlyCost");
        return totalMonthlyCost;
    }

    @Override
    public Customer getCustomerById(UUID customerId) {
        logger.info("[START] - CustomerSpringDataJPAService - getCustomerById");
        Optional<Customer> customer = customerRepository.findById(customerId);
        logger.info("found Customer {}", customerId);
        logger.info("[FINISH] - CustomerSpringDataJPAService - getCustomerById");
        return customer.orElseThrow(() -> ApiException.throwApiException(HttpStatus.NOT_FOUND, "customer not found"));
    }

    @Override
    public void updateCustomer(Customer customer) {
        logger.info("[START] - CustomerSpringDataJPAService - updateCustomer");
        customerRepository.update(customer);
        logger.info("updated Customer {}", customer.getId());
        logger.info("[FINISH] - CustomerSpringDataJPAService - updateCustomer");
    }
}
