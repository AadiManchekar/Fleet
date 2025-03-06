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
package com.fleet.init_vault.infrastructure.postgresql;

import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springframework.vault.core.VaultTemplate;

@Component
@Slf4j
@DependsOn("vaultHealthChecker")
public class VaultPostgresInitializer {

    private final VaultTemplate vaultTemplate;

    public VaultPostgresInitializer(VaultTemplate vaultTemplate) {
        this.vaultTemplate = vaultTemplate;
    }

    @PostConstruct
    public void init() {
        enableDatabaseSecretsEngine();
        configurePostgresDatabase("ride");
        configurePostgresDatabase("payment");
    }

    private void enableDatabaseSecretsEngine() {
        log.info("Enabling database secrets engine...");
        vaultTemplate.write("sys/mounts/database", Map.of("type", "database"));
    }

    private void configurePostgresDatabase(String dbName) {
        // Configure database connection
        Map<String, Object> dbConfig = new HashMap<>();
        dbConfig.put("plugin_name", "postgresql-database-plugin");
        dbConfig.put("allowed_roles", "readonly-" + dbName + ",readwrite-" + dbName);
        dbConfig.put(
                "connection_url",
                "postgresql://{{username}}:{{password}}@postgres-0:5432/"
                        + dbName
                        + "?sslmode=disable");
        dbConfig.put("username", "postgres");
        dbConfig.put("password", "postgrespassword");
        vaultTemplate.write("database/config/" + dbName, dbConfig);
        log.info("Configured database connection for " + dbName);

        // Configure read-only role with auto-rotating credentials
        Map<String, Object> readonlyRoleConfig = new HashMap<>();
        readonlyRoleConfig.put("db_name", dbName);
        readonlyRoleConfig.put(
                "creation_statements",
                "CREATE ROLE \"{{name}}\" WITH LOGIN PASSWORD '{{password}}' VALID UNTIL"
                    + " '{{expiration}}' INHERIT; GRANT ro TO \"{{name}}\"; GRANT USAGE ON SCHEMA"
                    + " public TO \"{{name}}\"; GRANT SELECT ON ALL TABLES IN SCHEMA public TO"
                    + " \"{{name}}\";");
        readonlyRoleConfig.put("default_ttl", "1h");
        readonlyRoleConfig.put("max_ttl", "24h");
        vaultTemplate.write("database/roles/readonly-" + dbName, readonlyRoleConfig);
        log.info("Configured read-only role for " + dbName);

        // Configure read-write role with auto-rotating credentials
        Map<String, Object> readwriteRoleConfig = new HashMap<>();
        readwriteRoleConfig.put("db_name", dbName);
        readwriteRoleConfig.put(
                "creation_statements",
                "CREATE ROLE \"{{name}}\" WITH LOGIN PASSWORD '{{password}}' VALID UNTIL"
                    + " '{{expiration}}' INHERIT; GRANT rw TO \"{{name}}\"; GRANT ALL PRIVILEGES ON"
                    + " SCHEMA public TO \"{{name}}\";");
        readwriteRoleConfig.put("default_ttl", "1h");
        readwriteRoleConfig.put("max_ttl", "24h");
        vaultTemplate.write("database/roles/readwrite-" + dbName, readwriteRoleConfig);
        log.info("Configured read-write role for " + dbName);
    }
}
