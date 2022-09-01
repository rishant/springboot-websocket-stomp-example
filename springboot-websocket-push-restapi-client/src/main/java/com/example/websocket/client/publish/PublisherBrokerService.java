package com.example.websocket.client.publish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PublisherBrokerService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Value("socketServerUri")
	private String socketServerUri;
	
	public ResponseEntity<String> sendPublicMessage(String uri, String message) {
		return restTemplate.postForEntity(socketServerUri + uri, message, String.class);
	}
}
