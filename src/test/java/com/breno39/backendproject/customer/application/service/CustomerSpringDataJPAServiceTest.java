package com.breno39.backendproject.customer.application.service;

import com.breno39.backendproject.credencial.domain.Credential;
import com.breno39.backendproject.customer.application.repository.CustomerRepository;
import com.breno39.backendproject.customer.domain.Customer;
import com.breno39.backendproject.handler.ApiException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerSpringDataJPAServiceTest {
    private static final UUID DEVICE_ID = UUID.randomUUID();
    public static final UUID CUSTOMER_ID = UUID.randomUUID();
    public static final String PASSWORD = "password";

    @InjectMocks
    private CustomerSpringDataJPAService service;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    void mustCreateCustomer() {
        var credential = Credential.builder().username("test").password(PASSWORD).build();
        var customer = Customer.builder().id(null).credential(credential).build();

        when(customerRepository.createCustomer(customer)).thenReturn(customer);

        var returnedCustomer= service.createCustomer(customer);

        Assertions.assertTrue(new BCryptPasswordEncoder().matches(PASSWORD, returnedCustomer.getCredential().getPassword()));
    }

    @Test
    void mustThrowApiExceptionAndNotCreateCustomer() {
        var credential = Credential.builder().username("test").password(PASSWORD).build();
        var customer = Customer.builder().id(null).credential(credential).build();

        when(customerRepository.createCustomer(customer))
                .thenThrow(new DataIntegrityViolationException(""));

        Assertions.assertThrows(ApiException.class, () -> service.createCustomer(customer));
    }

    @Test
    void mustReturnTotalMonthlyCost() {
        var customer = Customer.builder().id(CUSTOMER_ID).build();

        when(customerRepository.findTotalMonthlyCostById(CUSTOMER_ID)).thenReturn(Optional.ofNullable(customer.getTotalMonthlyCost()));

        var totalCost = service.getTotalMonthlyCost(CUSTOMER_ID);

        Assertions.assertEquals(customer.getTotalMonthlyCost(), totalCost);
    }

    @Test
    void mustThrowApiExceptionAndNotReturnTotalMonthlyCost() {
        var customer = Customer.builder().id(CUSTOMER_ID).build();

        when(customerRepository.findTotalMonthlyCostById(CUSTOMER_ID)).thenThrow(ApiException.throwApiException(HttpStatus.NOT_FOUND, ""));

        Assertions.assertThrows(ApiException.class, () -> service.getTotalMonthlyCost(CUSTOMER_ID));
    }

    @Test
    void mustReturnCustomerById() {
        var customer = Customer.builder().id(CUSTOMER_ID).build();

        when(customerRepository.findById(CUSTOMER_ID)).thenReturn(Optional.ofNullable(customer));

        var returnedCustomer = service.getCustomerById(CUSTOMER_ID);

        assertEquals(customer, returnedCustomer);
    }

    @Test
    void mustThrowApiExceptionAndNotReturnCustomerById() {
        var customer = Customer.builder().id(CUSTOMER_ID).build();

        when(customerRepository.findById(CUSTOMER_ID)).thenThrow(ApiException.throwApiException(HttpStatus.NOT_FOUND, ""));

        Assertions.assertThrows(ApiException.class, () -> service.getCustomerById(CUSTOMER_ID));
    }
}