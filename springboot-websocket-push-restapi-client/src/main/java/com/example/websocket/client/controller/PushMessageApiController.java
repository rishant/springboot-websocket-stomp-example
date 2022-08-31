package com.example.websocket.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class PushMessageApiController {

	@Autowired
	private RestTemplate restTemplate;
	
	@Value("socketServerUri")
	private String socketServerUri;
	
	@PostMapping("/send/public/message")
	public ResponseEntity<String> sendPublicMessage(String message){
		return restTemplate.postForEntity(socketServerUri+"/v1/api/public/message", message, String.class);
	}
	
	@PostMapping("/send/private/{id}/message")
	public ResponseEntity<String> sendPrivateMessage(@PathVariable(name = "id") String id, @RequestBody String body){
		return restTemplate.postForEntity(socketServerUri+"/v1/api/private/"+id+"/message", body, String.class);		
	}
}
