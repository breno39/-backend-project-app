package com.breno39.backendproject.credencial.application.api;

import java.net.URI;

import com.breno39.backendproject.credencial.application.service.CredentialService;
import com.breno39.backendproject.credencial.domain.Credential;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@Tag(name = "Credential API", description = "Contains the CRUD of the Credential domain")
public class CredentialRESTController implements CredentialAPI {
	public static final String CREDENTIAL_CREATE_PATH = "/app/admin/v1/credential/{credentialId}";

	private final Logger logger = LoggerFactory.getLogger(CredentialRESTController.class);

	private final CredentialService credentialService;

	@Override
	@Operation(operationId = "createCredential", description = "End-point used create a credential which are used to authenticate into the app", summary = "Create new credential")
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