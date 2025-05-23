package com.aadi.dummy_child_module.infrastructure.configuration;

import jakarta.annotation.PostConstruct;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Slf4j
public class VaultConfiguration {

    // GET static secrets from vault
    @Value("${db.username:default-username}")
    private String username;

    @Value("${db.password:default-password}")
    private String password;

    @Value("${secrettext:default-secret}")
    private String secretText;

    // Print the values
    @PostConstruct()
    public void init() {
        log.info("Username: " + username);
        log.info("Password: " + password);
        log.info("Secret Text: " + secretText);
    }
}
