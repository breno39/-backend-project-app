package com.ninjaone.backendinterviewproject.credencial.infrastructure;

import java.util.Optional;
import java.util.UUID;

import com.ninjaone.backendinterviewproject.credencial.application.repository.CredentialRepository;
import com.ninjaone.backendinterviewproject.credencial.domain.Credential;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
@AllArgsConstructor
public class CredentialJPARepository implements CredentialRepository {
	private final CredentialSpringDataJpaRepository credentialSpringDataJpaRepository;

	@Override
	public Credential saveCredential(Credential buildCredential) {
		log.info("[START] CredentialJPARepository - saveCredential");
		credentialSpringDataJpaRepository.save(buildCredential);
		log.info("[FINISH] CredentialJPARepository - saveCredential");
		return buildCredential;
	}

	@Override
	public Optional<Credential> findCredentialById(UUID id) {
		log.info("[START] CredentialJPARepository - findCredentialById");
		Optional<Credential> credencialOpt = credentialSpringDataJpaRepository.findById(id);
		log.info("[FINISH] CredentialJPARepository - findCredentialById");
		return credencialOpt;
	}
	
	@Override
	public Optional<Credential> findCredentialByUsername(String username) {
		log.info("[START] CredentialJPARepository - findCredentialByUsername");
		Optional<Credential> credencial = credentialSpringDataJpaRepository.findByUsername(username);
		log.info("[FINISH] CredentialJPARepository - findCredentialByUsername");
		return credencial;
	}
}