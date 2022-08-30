package com.example.websocket.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeRestController {

	@GetMapping("/")
	public String home() {
		return "<h1>Welcome to Websocket server...</h1>";
	}
}
