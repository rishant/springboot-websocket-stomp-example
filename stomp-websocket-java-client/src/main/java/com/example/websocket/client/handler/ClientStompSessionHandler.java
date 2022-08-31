package com.example.websocket.client.handler;

import java.lang.reflect.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

public class ClientStompSessionHandler extends StompSessionHandlerAdapter {
    private static final Logger logger = LoggerFactory.getLogger("ClientStompSessionHandler");

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        logger.info("New session established. Session -> {}", session);
        logger.info("New session established. StompHeaders -> {}", connectedHeaders);
        logger.info("New session established. Session Id -> {}", session.getSessionId());
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
    	logger.info("Stomp Headers -> {}", headers);
    	logger.info("Message Payload -> {}", payload);
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return String.class;
    }
}