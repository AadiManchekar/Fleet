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
package com.fleet.ride_booking_service.controller;

import com.fleet.ride_booking_service.domain.api.RideBookingService;
import com.fleet.ride_booking_service.domain.api.dto.RideBooking;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ride-booking")
public class RideBookingController {
    private final RideBookingService rideBookingService;

    RideBookingController(RideBookingService rideBookingService) {
        this.rideBookingService = rideBookingService;
    }

    @PostMapping("/book")
    public ResponseEntity<String> bookRide(@RequestBody RideBooking rideBooking) {

        String rideId = rideBookingService.bookRide(rideBooking);
        return ResponseEntity.status(HttpStatus.CREATED).body(rideId);
    }
}
