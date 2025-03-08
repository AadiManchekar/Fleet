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
package com.fleet.init_vault.client.stripe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fleet.init_vault.AbstractVaultClientTest;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class StripeTest extends AbstractVaultClientTest {

    public StripeTest() {
        super();
    }

    @Test
    void checkStripeSecrets() {
        try {
            request =
                    HttpRequest.newBuilder()
                            .uri(new URI("http://localhost:8200/v1/secret/data/api/google/maps"))
                            .headers("X-Vault-Token", VAULT_TOKEN)
                            .GET()
                            .build();

            response = client.send(request, BodyHandlers.ofString());
            assertNotNull(response.body(), "Stripe API response is null");
            assertEquals(false, response.body().isBlank());
            assertEquals(false, response.body().isEmpty());
        } catch (URISyntaxException | IOException | InterruptedException e) {
            log.error("Error occurred while sending request to Stripe API");
        }
    }
}
