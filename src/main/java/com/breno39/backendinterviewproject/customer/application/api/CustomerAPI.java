package com.breno39.backendinterviewproject.customer.application.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.UUID;

@RestController
public interface CustomerAPI {

    @PostMapping("public/v1/customer")
    @ResponseStatus(value = HttpStatus.CREATED)
    ResponseEntity<CustomerDTO> createCustomer(@RequestBody @Valid CustomerAndCredentialCreationForm customerForm,
                                               UriComponentsBuilder uriBuilder);

    @GetMapping("private/v1/customer/{customerId}/cost")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<CustomerTotalMonthlyCostDTO> getTotalMonthlyCost(@PathVariable UUID customerId);
}
