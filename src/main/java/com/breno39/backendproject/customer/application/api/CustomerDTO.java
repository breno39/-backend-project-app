package com.breno39.backendproject.customer.application.api;

import com.breno39.backendproject.customer.domain.Customer;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class CustomerDTO {
    private UUID credentialId;

    public CustomerDTO(Customer createdCustomer) {
        credentialId = createdCustomer.getCredential().getId();
    }
}
