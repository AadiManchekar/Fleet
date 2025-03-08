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
package com.fleet.init_vault;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public abstract class AbstractVaultClientTest {

    protected HttpClient client;
    protected HttpRequest request;
    protected HttpResponse<String> response;

    protected static String VAULT_TOKEN = "00000000-0000-0000-0000-000000000000";

    protected AbstractVaultClientTest() {
        client = HttpClient.newHttpClient();
    }
}
