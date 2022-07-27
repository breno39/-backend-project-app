package com.ninjaone.backendinterviewproject.credencial.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class CredentialTest {

    public static final String PASSWORD = "123456";

    @Test
    void mustEncryptPassword() {
        var credential = Credential.builder().password(PASSWORD).build();
        var encoder = new BCryptPasswordEncoder();

        credential.encryptPassword();

        Assertions.assertTrue(encoder.matches(PASSWORD, credential.getPassword()));
    }
}