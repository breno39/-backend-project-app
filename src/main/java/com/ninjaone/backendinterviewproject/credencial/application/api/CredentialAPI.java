package com.ninjaone.backendinterviewproject.credencial.application.api;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("public/v1/credential")
public interface CredentialAPI {
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<CredentialDTO> createCredential(@RequestBody @Valid CredentialForm credentialForm,
														  UriComponentsBuilder uriBuilder);

}
