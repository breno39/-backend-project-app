package com.breno39.backendinterviewproject.customer.infrastructure;

import com.breno39.backendinterviewproject.customer.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerSpringDataJPARepository extends JpaRepository<Customer, UUID> {

    @Query(name = "Customer.getTotalMonthlyCostById")
    Optional<Long> getTotalMonthlyCostById(@Param("customerId") UUID customerId);
}
