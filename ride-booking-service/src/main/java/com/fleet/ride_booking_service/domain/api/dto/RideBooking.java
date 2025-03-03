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
package com.fleet.ride_booking_service.domain.api.dto;

import java.time.Instant;

public record RideBooking(
        String customerId,
        Instant bookingTime,
        Location sourceLocation,
        Location destinationLocation) {}
