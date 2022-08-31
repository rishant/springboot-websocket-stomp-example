package com.example.websocket.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

/**
 * @author rishantgupta007@gmail.com
 * 
 * REST Message Handler for STOMP. Message Handler Routing prefix key "/v1/api"
 * MessageBroker Routing key for pub/sub "/public", "/private"
 */
@RestController
@RequestMapping("/v1/api")
public class RestApiController {
 
	private Logger logger = LoggerFactory.getLogger(RestApiController.class);
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	@PostMapping("/public/message")
	private ResponseEntity<String> sendPublicMessage(@RequestBody String message) {
		logger.info("Public message request.");
		messagingTemplate.convertAndSend("/topic/public/message", HtmlUtils.htmlEscape(message, "UTF-8"));
		return new ResponseEntity<>("Message fanout to public routing key.", HttpStatus.OK);
	}

	@PostMapping("/private/{id}/message")
	private ResponseEntity<?> sendPrivateMessage(@PathVariable(name = "id") String id, @RequestBody String message) {
		logger.info("Private message request.");
		messagingTemplate.convertAndSend("/topic/private/" + id + "/message", HtmlUtils.htmlEscape(message, "UTF-8"));
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
