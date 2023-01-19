package com.breno39.backendinterviewproject.customer.application.api;

import com.breno39.backendinterviewproject.credencial.application.api.CredentialForm;
import com.breno39.backendinterviewproject.customer.domain.Customer;
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
