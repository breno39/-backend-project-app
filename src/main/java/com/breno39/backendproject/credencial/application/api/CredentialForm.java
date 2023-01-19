package com.breno39.backendproject.credencial.application.api;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.breno39.backendproject.credencial.domain.Credential;
import lombok.Value;

@Value
public class CredentialForm {
	@NotBlank
	private String username;

	@Size(min = 4)
	@NotNull
	private String password;

	public Credential toEntity() {
		return Credential.builder().username(this.username).password(this.password).build();
	}

}