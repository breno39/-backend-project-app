package com.ninjaone.backendinterviewproject.customer.application.api;

import com.ninjaone.backendinterviewproject.credencial.application.api.CredentialForm;
import com.ninjaone.backendinterviewproject.customer.domain.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CustomerAndCredentialCreationForm {

    @Valid
    private CredentialForm credentialForm;

    public Customer toEntity() {
        return Customer.builder()
                .credential(credentialForm.toEntity())
                .build();
    }
}
