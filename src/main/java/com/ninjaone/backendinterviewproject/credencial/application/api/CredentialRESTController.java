package com.ninjaone.backendinterviewproject.credencial.application.api;

import java.net.URI;

import com.ninjaone.backendinterviewproject.credencial.application.service.CredentialService;
import com.ninjaone.backendinterviewproject.credencial.domain.Credential;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequiredArgsConstructor
public class CredentialRESTController implements CredentialAPI {
	public static final String CREDENTIAL_CREATE_PATH = "/app/admin/v1/credential/{credentialId}";

	private final Logger logger = LoggerFactory.getLogger(CredentialRESTController.class);

	private final CredentialService credentialService;

	@Override
	public ResponseEntity<CredentialDTO> createCredential(CredentialForm credentialForm,
														  UriComponentsBuilder uriBuilder) {
		logger.info("[START] CredentialRESTController - createCredential");
		Credential createdCredential = credentialService.createCredential(credentialForm.toEntity());
		URI uri = uriBuilder.path(CREDENTIAL_CREATE_PATH)
				.buildAndExpand(createdCredential.getId()).toUri();
		logger.info("created credential with id: {}", createdCredential.getId());
		logger.info("[FINISH] CredentialRESTController - createCredential");
		return ResponseEntity.created(uri).body(new CredentialDTO(createdCredential));
	}

}