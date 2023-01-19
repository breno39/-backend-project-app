package com.breno39.backendproject.credencial.application.service;

import com.breno39.backendproject.credencial.application.repository.CredentialRepository;
import com.breno39.backendproject.credencial.domain.Credential;
import com.breno39.backendproject.handler.ApiException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
@Log4j2
public class CredentialSpringDataJpaService implements CredentialService {
	private CredentialRepository credentialRepository;

	@Override
	public Credential createCredential(Credential credentialByForm) {
		log.info("[START] CredentialSpringDataJpaService - createCredential");
		credentialByForm.encryptPassword();
		saveToRepository(credentialByForm);
		log.info("[FINISH] CredentialSpringDataJpaService - createCredential");
		return credentialByForm;
	}

	@Override
	public Credential findCredentialById(UUID id) {
		log.info("[START] CredentialSpringDataJpaService - findCredentialById");
		var credencial = findCredentialByIdOrElseThrow(id);
		log.info("[FINISH] CredentialSpringDataJpaService - findCredentialById");
		return credencial;
	}
	
	@Override
	public Credential findCredentialByUsername(String username) {
		log.info("[START] CredentialSpringDataJpaService - findCredentialById");
		Credential credential = findCredentialByUsernameOrElseThrow(username);
		log.info("[FINISH] CredentialSpringDataJpaService - findCredentialById");
		return credential;
	}

	private Credential saveToRepository(Credential credential) {
		try {
			return credentialRepository.saveCredential(credential);
		} catch (DataIntegrityViolationException e) {
			throw ApiException.throwApiException(HttpStatus.BAD_REQUEST, "Credential not saved", e);
		}
	}

	private Credential findCredentialByIdOrElseThrow(UUID id) {
		return credentialRepository.findCredentialById(id).orElseThrow(() -> ApiException
				.throwApiException(HttpStatus.NOT_FOUND, "No credential with given id"));
	}

	private Credential findCredentialByUsernameOrElseThrow(String username) {
		return credentialRepository.findCredentialByUsername(username).orElseThrow(() -> ApiException
				.throwApiException(HttpStatus.NOT_FOUND, "No credential with given username"));
	}

}
