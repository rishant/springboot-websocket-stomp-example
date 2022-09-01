package com.example.websocket.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.websocket.client.publish.PublisherBrokerService;

@RestController
public class PushMessageApiController {
	
	@Autowired
	private PublisherBrokerService publisherBrokerService;
	
	@PostMapping("/send/public/message")
	public ResponseEntity<String> sendPublicMessage(String message){
		return publisherBrokerService.sendPublicMessage("/v1/api/public/message", message);
	}
	
	@PostMapping("/send/private/{id}/message")
	public ResponseEntity<String> sendPrivateMessage(@PathVariable(name = "id") String id, @RequestBody String body){
		return publisherBrokerService.sendPublicMessage("/v1/api/private/"+id+"/message", body);
	}
}
