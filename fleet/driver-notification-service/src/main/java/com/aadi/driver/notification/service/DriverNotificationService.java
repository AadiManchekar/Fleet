package com.aadi.driver.notification.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class DriverNotificationService {
    
    private final DriverConnectionService driverConnectionService;

    public void notifyDriver(String driverId, String message) {
        final WebSocketSession session = driverConnectionService.getConnection(driverId);

        if (session != null && session.isOpen()) {
            try {
                session.sendMessage(new TextMessage(message));
            } catch (IOException e) {
                log.error("Error while sending message to driver with id: {}", driverId);
            }
        } else {
            log.error("Driver with id: {} is not connected", driverId);
        }
    }
}
