package com.breno39.backendinterviewproject.credencial.application.api;

import javax.validation.Valid;

import com.breno39.backendinterviewproject.credencial.domain.Credential;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@Valid
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class CredentialDTO {

	public CredentialDTO(Credential credential) {
		this.username = credential.getUsername();
	}

	private String username;

}
