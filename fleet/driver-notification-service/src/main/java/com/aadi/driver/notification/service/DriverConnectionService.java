package com.aadi.driver.notification.service;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DriverConnectionService {
    
    // We will have an inmemory map to store the driver sessions. Stores: [driverId, WebSocketSession]
    // Mainly for simplicity, in production we can use Redis or any other caching mechanism
    private final ConcurrentHashMap<String, WebSocketSession> driverSessions = new ConcurrentHashMap<>();

    public void addConnection(String driverId, WebSocketSession session) {
        // Storing the driverId in the session attributes as it ease the process of removing the driver session
        // if we dont have the driverId, we need to iterate over the map to find the driver session
        // which is not efficient, since we have the driverId in the session attributes, we can directly remove the driver session
        session.getAttributes().put("driverID", driverId);
        driverSessions.put(driverId, session);
    }

    public String removeConnection(String driverId) {
        if (driverSessions.containsKey(driverId)) {
            driverSessions.remove(driverId);
            log.info(driverId + " removed from driverSessions");
            return driverId;
        } else {
            log.error("Driver with id {} not found in driverSessions", driverId);
            return null;
        }
    }

    public boolean isDriverConnected(String driverId) {
        return driverSessions.containsKey(driverId);
    }

    public WebSocketSession getConnection(String driverId) {
        if (isDriverConnected(driverId)) {
            return driverSessions.get(driverId);
        }
        return null;
    }

}
