package com.ninjaone.backendinterviewproject.customer.application.api;

import com.ninjaone.backendinterviewproject.customer.domain.Customer;
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
