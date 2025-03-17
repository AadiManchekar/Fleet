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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fleet.init_vault.AbstractVaultClientTest;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

@Slf4j
public class FeatureFlagTest extends AbstractVaultClientTest {
    public FeatureFlagTest() {
        super();
    }

    @Test
    void checkDisableDifferentiateVehicleType() {
        try {
            request =
                    HttpRequest.newBuilder()
                            .uri(
                                    new URI(
                                            "http://localhost:8200/v1/secret/data/internal/feature_flag"))
                            .headers("X-Vault-Token", VAULT_TOKEN)
                            .GET()
                            .build();

            response = client.send(request, BodyHandlers.ofString());
            assertNotNull(
                    response.body(), "Feature Flag differentiateVehicleType API response is null");
            assertEquals(false, response.body().isBlank());
            assertEquals(false, response.body().isEmpty());

            // Parse the JSON response and check the value of the feature flag
            JSONObject jsonResponse = new JSONObject(response.body());
            JSONObject data = jsonResponse.getJSONObject("data").getJSONObject("data");
            boolean differentiateVehicleType = data.getBoolean("differentiateVehicleType");
            assertEquals(
                    false,
                    differentiateVehicleType,
                    "Feature flag differentiateVehicleType should be false");
        } catch (URISyntaxException | IOException | InterruptedException | JSONException e) {
            log.error(
                    "Error occurred while sending request to Feature Flag differentiateVehicleType"
                            + " API",
                    e);
        }
    }

    @Test
    void checkEnableAutoIncrementSearchRadius() {
        try {
            request =
                    HttpRequest.newBuilder()
                            .uri(
                                    new URI(
                                            "http://localhost:8200/v1/secret/data/internal/feature_flag"))
                            .headers("X-Vault-Token", VAULT_TOKEN)
                            .GET()
                            .build();

            response = client.send(request, BodyHandlers.ofString());
            assertNotNull(
                    response.body(),
                    "Feature Flag enableAutoIncrementSearchRadius API response is null");
            assertEquals(false, response.body().isBlank());
            assertEquals(false, response.body().isEmpty());

            // Parse the JSON response and check the value of the feature flag
            JSONObject jsonResponse = new JSONObject(response.body());
            JSONObject data = jsonResponse.getJSONObject("data").getJSONObject("data");
            boolean enableAutoIncrementSearchRadius =
                    data.getBoolean("enableAutoIncrementSearchRadius");
            assertEquals(
                    false,
                    enableAutoIncrementSearchRadius,
                    "Feature flag enableAutoIncrementSearchRadius should be false");
        } catch (URISyntaxException | IOException | InterruptedException | JSONException e) {
            log.error(
                    "Error occurred while sending request to Feature Flag"
                            + " enableAutoIncrementSearchRadius API",
                    e);
        }
    }
}
