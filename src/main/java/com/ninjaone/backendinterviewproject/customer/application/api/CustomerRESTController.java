package com.ninjaone.backendinterviewproject.customer.application.api;

import com.ninjaone.backendinterviewproject.customer.application.service.CustomerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
public class CustomerRESTController implements CustomerAPI{
    public static final String CUSTOMER_CREATE_PATH = "/public/v1/customer/{customerId}";

    private Logger logger = LoggerFactory.getLogger(CustomerRESTController.class);

    private final CustomerService service;

    @Override
    public ResponseEntity<CustomerDTO> createCustomer(CustomerAndCredentialCreationForm customerForm, UriComponentsBuilder uriBuilder) {
        logger.info("[START] - CustomerSpringDataJPAService - createCustomer");
        var createdCustomer = service.createCustomer(customerForm.toEntity());
        URI uri = uriBuilder.path(CUSTOMER_CREATE_PATH).buildAndExpand(createdCustomer.getId()).toUri();
        logger.info("[FINISH] - CustomerSpringDataJPAService - createCustomer");
        return ResponseEntity.created(uri).body(new CustomerDTO(createdCustomer));
    }

    @Override
    @SecurityRequirement(name = "openApiAuth")
    public ResponseEntity<CustomerTotalMonthlyCostDTO> getTotalMonthlyCost(UUID customerId) {
        logger.info("[START] - CustomerSpringDataJPAService - getTotalMonthlyCost");
        var totalMonthlyCost = service.getTotalMonthlyCost(customerId);
        logger.info("[FINISH] - CustomerSpringDataJPAService - getTotalMonthlyCost");
        return ResponseEntity.ok(new CustomerTotalMonthlyCostDTO(totalMonthlyCost));
    }
}
