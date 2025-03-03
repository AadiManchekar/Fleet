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
package com.fleet.init_vault.client.google_map;

import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springframework.vault.core.VaultTemplate;

@Component
@Slf4j
@DependsOn("vaultHealthChecker")
public class GoogleMap {

    private final VaultTemplate vaultTemplate;

    public GoogleMap(VaultTemplate vaultTemplate) {
        this.vaultTemplate = vaultTemplate;
    }

    @PostConstruct
    public void init() {
        writeSecretsToVault();
    }

    private void writeSecretsToVault() {
        Map<String, String> secrets = new HashMap<>();
        secrets.put("key", UUID.randomUUID().toString());

        Map<String, Object> data = new HashMap<>();
        data.put("data", secrets);

        vaultTemplate.write("secret/data/api/google/maps", data);
        log.info("Google Maps API Key has been written to Vault");
    }
}
