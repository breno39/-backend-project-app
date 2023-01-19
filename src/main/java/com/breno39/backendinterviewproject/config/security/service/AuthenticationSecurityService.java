package com.breno39.backendinterviewproject.config.security.service;

import com.breno39.backendinterviewproject.credencial.infrastructure.CredentialSpringDataJpaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@AllArgsConstructor
public class AuthenticationSecurityService implements UserDetailsService {

    private CredentialSpringDataJpaRepository credentialRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("[START] AuthenticationSecurityService - loadUserByUsername");
        var credential = credentialRepository.findByUsername(username);
        log.info("[FINISH] AuthenticationSecurityService - loadUserByUsername");
        return credential
                .orElseThrow(() -> new UsernameNotFoundException(""));
    }
}
