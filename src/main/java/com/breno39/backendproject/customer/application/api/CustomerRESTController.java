package com.breno39.backendproject.customer.application.api;

import com.breno39.backendproject.customer.application.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Tag(name = "Customer API", description = "Contains the CRUD of the Customer domain, contains endpoints to add/remove Devices and get the total monthly cost of the customer")
public class CustomerRESTController implements CustomerAPI{
    public static final String CUSTOMER_CREATE_PATH = "/public/v1/customer/{customerId}";

    private Logger logger = LoggerFactory.getLogger(CustomerRESTController.class);

    private final CustomerService service;

    @Override
    @Operation(operationId = "createCustomer", description = "End-point used to create a new customer, it also creates an associated credential to authenticate", summary = "create new customer")
    public ResponseEntity<CustomerDTO> createCustomer(CustomerAndCredentialCreationForm customerForm, UriComponentsBuilder uriBuilder) {
        logger.info("[START] - CustomerSpringDataJPAService - createCustomer");
        var createdCustomer = service.createCustomer(customerForm.toEntity());
        URI uri = uriBuilder.path(CUSTOMER_CREATE_PATH).buildAndExpand(createdCustomer.getId()).toUri();
        logger.info("[FINISH] - CustomerSpringDataJPAService - createCustomer");
        return ResponseEntity.created(uri).body(new CustomerDTO(createdCustomer));
    }

    @Override
    @SecurityRequirement(name = "openApiAuth")
    @Operation(operationId = "getTotalMonthlyCost", description = "End-point used to retrieve the total cost of all devices and services a Customer has", summary = "Get total Customer cost")
    public ResponseEntity<CustomerTotalMonthlyCostDTO> getTotalMonthlyCost(UUID customerId) {
        logger.info("[START] - CustomerSpringDataJPAService - getTotalMonthlyCost");
        var totalMonthlyCost = service.getTotalMonthlyCost(customerId);
        logger.info("[FINISH] - CustomerSpringDataJPAService - getTotalMonthlyCost");
        return ResponseEntity.ok(new CustomerTotalMonthlyCostDTO(totalMonthlyCost));
    }
}
