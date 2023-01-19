package com.breno39.backendinterviewproject.customer.infrastructure;

import com.breno39.backendinterviewproject.customer.domain.Customer;
import com.breno39.backendinterviewproject.customer.application.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CustomerJpaRepository implements CustomerRepository {
    private Logger logger = LoggerFactory.getLogger(CustomerJpaRepository.class);

    private final CustomerSpringDataJPARepository customerSpringDataJPARepository;

    @Override
    public Customer createCustomer(Customer customer) {
        logger.info("[START] - CustomerJpaRepository - createCustomer");
        var createdCustomer = customerSpringDataJPARepository.save(customer);
        logger.info("Customer {} created", createdCustomer.getId());
        logger.info("[FINISH] - CustomerJpaRepository - createCustomer");
        return createdCustomer;
    }

    @Override
    public Optional<Long> findTotalMonthlyCostById(UUID customerId) {
        logger.info("[START] - CustomerJpaRepository - findTotalMonthlyCostById");
        var totalMonthlyCost = customerSpringDataJPARepository.getTotalMonthlyCostById(customerId);
        logger.info("[FINISH] - CustomerJpaRepository - findTotalMonthlyCostById");
        return totalMonthlyCost;
    }

    @Override
    public Optional<Customer> findById(UUID customerId) {
        logger.info("[START] - CustomerJpaRepository - findById");
        var customer = customerSpringDataJPARepository.findById(customerId);
        logger.info("[FINISH] - CustomerJpaRepository - findById");
        return customer;
    }

    @Override
    public void update(Customer customer) {
        logger.info("[START] - createCustomer - CustomerJpaRepository");
        var createdCustomer = customerSpringDataJPARepository.saveAndFlush(customer);
        logger.info("Customer {} updated", createdCustomer.getId());
        logger.info("[FINISH] - createCustomer - CustomerJpaRepository");
    }
}
