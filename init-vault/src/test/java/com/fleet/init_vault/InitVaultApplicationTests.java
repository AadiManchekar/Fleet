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

import com.fleet.init_vault.client.google_map.GoogleMapTest;
import com.fleet.init_vault.client.send_grid.SendGridTest;
import com.fleet.init_vault.client.stripe.StripeTest;
import com.fleet.init_vault.infrastructure.vault.FeatureFlagTest;
import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Suite
@SelectClasses({
    InitVaultApplicationTests.class,
    GoogleMapTest.class,
    SendGridTest.class,
    StripeTest.class,
    FeatureFlagTest.class
})
class InitVaultApplicationTests {

    @Test
    void contextLoads() {}
}
