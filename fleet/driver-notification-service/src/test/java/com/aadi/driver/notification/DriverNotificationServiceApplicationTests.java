package com.aadi.driver.notification;

import com.aadi.driver.notification.handler.WebSocketHandler;
import com.aadi.driver.notification.service.DriverConnectionService;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DriverNotificationServiceApplicationTests {

    @LocalServerPort
    private Integer port;

    @Mock
    private WebSocketHandler webSocketHandler;

    @Autowired
    private DriverConnectionService driverConnectionService;

    private WebSocketSession session;

    @BeforeEach
    void initiateDriverWSConnection() {
        
        StandardWebSocketClient client = new StandardWebSocketClient();

        try {
            session = client
                    .execute(webSocketHandler, "ws://localhost:" + port + "/ws?driverID=123")
                    .get(10, TimeUnit.SECONDS);

        } catch (InterruptedException e) {
            e.printStackTrace();
            Assertions.fail("Test failed due to InterruptedException");
        } catch (ExecutionException e) {
            e.printStackTrace();
            Assertions.fail("Test failed due to ExecutionException");
        } catch (TimeoutException e) {
            e.printStackTrace();
            Assertions.fail("Test failed due to TimeoutException");
        }
    }

    @AfterEach
    void closeDriverWSConnection() {
        if(!session.isOpen())
        {
            log.info("Session is already closed");
            return;
        }
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
            Assertions.fail("Test failed due to IOException");
        }
    }

    @Test
    void isDriverConnectionEstablished() {
        Assertions.assertNotNull(session, "Driver WebSocket session is null");
        Assertions.assertTrue(session.isOpen(), "The WebSocket session should be open");
    }

    @Test 
    void isDriverConnectionPersisted() {
        Assertions.assertTrue(driverConnectionService.isDriverConnected("123"), "Driver connection is not persisted");
    }

    @Test
    void isDriverIDStoredInSession() {
        Assertions.assertEquals("123", driverConnectionService.getConnection("123").getAttributes().get("driverID"), "Driver ID is not stored in the session");
    }

    @Test
    void isDriverLocationUpdateReceived() {
        String payload = """
                {
                    "driverID": "123",
                    "latitude": 16.34,
                    "longitude": 16.78
                }
                """;
        TextMessage message = new TextMessage(payload);
        try {
            session.sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
            Assertions.fail("Test failed due to IOException");
        }
    }

    @Test
    void isDriverConnectionRemoved() {
        try {
            session.close();
            // wait to make sure the connection is closed and the driver is removed from the driverSessions
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Assertions.fail("Test failed due to InterruptedException");
        } catch (IOException e) {
            e.printStackTrace();
            Assertions.fail("Test failed due to IOException");
        }
        Assertions.assertFalse(driverConnectionService.isDriverConnected("123"), "Driver connection is not removed");
    }

    @Test
    void getInvalidDriverConnection() {
        Assertions.assertNull(driverConnectionService.getConnection("456"), "Driver connection is not found");
    }

    @Test
    void removeInvalidDriverConnection() {
        Assertions.assertNull(driverConnectionService.removeConnection("456"), "Driver connection is removed");
    }
}
