package com.breno39.backendinterviewproject.credencial.application.service;

import com.breno39.backendinterviewproject.credencial.domain.Credential;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface CredentialService {

	Credential createCredential(Credential buildCredential);

	Credential findCredentialById(UUID id);

	Credential findCredentialByUsername(String username);

}
