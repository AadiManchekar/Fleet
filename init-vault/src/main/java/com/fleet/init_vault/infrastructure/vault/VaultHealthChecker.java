/*
    Fleet - Bike and Cab Aggregator Application
    Copyright (C) 2025 Aadi Manchekar

    For any modifications or feedback, please reach out to aadimanchekar2002@gmail.com
    or visit https://github.com/AadiManchekar

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package com.fleet.init_vault.infrastructure.vault;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultHealth;

@Component
@Slf4j
public class VaultHealthChecker {
    private final VaultTemplate vaultTemplate;

    public VaultHealthChecker(VaultTemplate vaultTemplate) {
        this.vaultTemplate = vaultTemplate;
    }

    @PostConstruct
    public void init() {
        checkVaultHealth();
    }

    private void checkVaultHealth() {
        VaultHealth health = vaultTemplate.opsForSys().health();

        if (!health.isInitialized()) {
            log.error("Vault is not initialized. Initialize it to continue");
            throw new RuntimeException("Vault is not initialized, health: " + health);
        } else if (health.isSealed()) {
            log.error("Vault is sealed. Unseal it to continue");
            throw new RuntimeException("Vault is sealed, health: " + health);
        } else {
            log.info("Vault is unsealed and initialized");
        }
    }
}
