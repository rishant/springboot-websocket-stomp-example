package com.example.websocket.controller;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.example.websocket.model.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author rishantgupta007@gmail.com
 *
 *         WebSocket MVC Message Handler for STOMP. Message Handler Routing
 *         prefix key "/app" MessageBroker Routing key for pub/sub "/topic"
 */
@Controller
public class WebSocketController {
	private Logger logger = LoggerFactory.getLogger(WebSocketController.class);
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	@Autowired
	private ObjectMapper objectMapper;

	@MessageMapping("/message")
	@SendTo("/chatroom/public")
	private Message publicMessage(SimpMessageHeaderAccessor headerAccessor, @Payload Message message) {
		logger.info("Public message request.");
		return message;
	}
	
	@MessageMapping("/private-message")
	private Message privateMessage(SimpMessageHeaderAccessor headerAccessor, @Payload Message message) {
		logger.info("private message request.");
		messagingTemplate.convertAndSend("/chatroom/" + message.getReceiverName() + "/private", message);
//		messagingTemplate.convertAndSendToUser(message.getReceiverName(), "/private", message);
		return message;
	}
		
	// /app/public/message
	@MessageMapping("/public/message")
	private void sendPublicMessage(SimpMessageHeaderAccessor headerAccessor, @Payload String message)
			throws JsonProcessingException {
		logger.info("Public message request.");
		@SuppressWarnings("unchecked")
		Map<String, Object> messageObject = objectMapper.readValue(message, Map.class);
		messagingTemplate.convertAndSend("/topic/public/message", messageObject);
	}

	// /app/private/{id}/message
	@MessageMapping("/private/{id}/message")
	private void sendPrivateMessage(SimpMessageHeaderAccessor headerAccessor, @DestinationVariable("id") String id,
			@Payload String message) throws JsonProcessingException {
		logger.info("Private message request.");
//		String sessionId = headerAccessor.getSessionAttributes().get("sessionId").toString();
//		System.out.println(sessionId);
//		headerAccessor.setSessionId(sessionId);
		@SuppressWarnings("unchecked")
		Map<String, Object> messageObject = objectMapper.readValue(message, Map.class);
		messagingTemplate.convertAndSend("/topic/private/" + id + "/message", messageObject.get("name"));
	}

}
