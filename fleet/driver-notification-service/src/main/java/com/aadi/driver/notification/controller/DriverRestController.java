package com.aadi.driver.notification.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aadi.driver.notification.service.DriverConnectionService;
import com.aadi.driver.notification.service.DriverNotificationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/notification/driver")
@RequiredArgsConstructor
public class DriverRestController {
    private final DriverConnectionService driverConnectionService;
    private final DriverNotificationService driverNotificationService;

    @PostMapping("/notify-drivers")
    public String notifyDrivers() {
        final List<String> driverIDs = List.of("123", "456", "789");
        driverIDs.forEach(driverID -> {
            if (driverConnectionService.isDriverConnected(driverID)) {
                driverNotificationService.notifyDriver(driverID, "Ride available at CID: 101, LAT: 12.34, LON: 56.78");
            }
        });
        return "Notification sent to connected drivers.";
    }
}