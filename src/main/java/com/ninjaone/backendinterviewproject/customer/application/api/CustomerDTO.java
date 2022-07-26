package com.ninjaone.backendinterviewproject.customer.application.api;

import com.ninjaone.backendinterviewproject.customer.domain.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class CustomerDTO {
    private UUID id;
    private UUID credentialId;
    public CustomerDTO(Customer createdCustomer) {
        id = createdCustomer.getId();
        credentialId = createdCustomer.getCredential().getId();
    }
}
