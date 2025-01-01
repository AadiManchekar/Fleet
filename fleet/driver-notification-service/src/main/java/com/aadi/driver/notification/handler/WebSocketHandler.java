package com.aadi.driver.notification.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.aadi.driver.notification.service.DriverConnectionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketHandler extends TextWebSocketHandler{

    private final DriverConnectionService driverConnectionService;
    
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        // URL format: ws://server/ws?driverID=123
        final String driverID = session.getUri().getQuery().split("=")[1]; 
        driverConnectionService.addConnection(driverID, session);
        log.info("Driver connected: {}", driverID);
    }

    // This method handles incoming driver's location updates
    // Example payload: {"driverID":"123", "latitude":12.34, "longitude":56.78}
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        final String payload = message.getPayload();
        log.info("Received message: {}", payload);

        // TODO: Implement logic to process the incoming location update
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        final String driverID = driverConnectionService.removeConnection(session.getAttributes().get("driverID").toString());
        
        if (driverID == null) {
            log.error("Driver not found in driverSessions");
            return;
        }
        
        log.info("Driver disconnected: {}", driverID);
    }
}
