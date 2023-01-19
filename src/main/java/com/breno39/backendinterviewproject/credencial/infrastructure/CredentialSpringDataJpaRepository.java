package com.breno39.backendinterviewproject.credencial.infrastructure;

import java.util.Optional;
import java.util.UUID;

import com.breno39.backendinterviewproject.credencial.domain.Credential;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CredentialSpringDataJpaRepository extends JpaRepository<Credential, UUID> {
    Optional<Credential> findByUsername(String username);

}
