package com.breno39.backendproject.credencial.application.repository;

import com.breno39.backendproject.credencial.domain.Credential;

import java.util.Optional;
import java.util.UUID;

public interface CredentialRepository {
	Credential saveCredential(Credential credential);
	Optional<Credential> findCredentialById(UUID id);
	Optional<Credential> findCredentialByUsername(String username);
}