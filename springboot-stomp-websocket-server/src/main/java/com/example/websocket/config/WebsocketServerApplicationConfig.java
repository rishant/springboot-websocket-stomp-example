package com.example.websocket.config;

import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;

public class WebsocketServerApplicationConfig {

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();		
	}
}
