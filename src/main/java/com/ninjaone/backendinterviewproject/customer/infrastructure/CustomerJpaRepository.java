package com.ninjaone.backendinterviewproject.customer.infrastructure;

import com.ninjaone.backendinterviewproject.customer.application.repository.CustomerRepository;
import com.ninjaone.backendinterviewproject.customer.domain.Customer;
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
        logger.info("[START] - createCustomer - CustomerJpaRepository");
        var createdCustomer = customerSpringDataJPARepository.save(customer);
        logger.info("Customer {} created", createdCustomer.getId());
        logger.info("[FINISH] - createCustomer - CustomerJpaRepository");
        return createdCustomer;
    }

    @Override
    public Optional<Long> findTotalMonthlyCostById(UUID customerId) {
        logger.info("[START] - createCustomer - CustomerJpaRepository");
        var totalMonthlyCost = customerSpringDataJPARepository.getTotalMonthlyCostById(customerId);
        logger.info("[FINISH] - createCustomer - CustomerJpaRepository");
        return totalMonthlyCost;
    }

    @Override
    public Optional<Customer> findById(UUID customerId) {
        logger.info("[START] - createCustomer - CustomerJpaRepository");
        var customer = customerSpringDataJPARepository.findById(customerId);
        logger.info("[FINISH] - createCustomer - CustomerJpaRepository");
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
