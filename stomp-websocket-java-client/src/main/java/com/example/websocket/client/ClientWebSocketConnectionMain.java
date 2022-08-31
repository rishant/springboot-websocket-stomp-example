package com.example.websocket.client;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import com.example.websocket.client.handler.ClientStompSessionHandler;
import com.fasterxml.jackson.core.JsonProcessingException;

public class ClientWebSocketConnectionMain {
	private static Logger logger = LoggerFactory.getLogger(ClientWebSocketConnectionMain.class);

	private static com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();

	private static final String URL = "ws://localhost:8181/ws-socket";

	public static void main(String[] args) throws InterruptedException, ExecutionException, JsonProcessingException {
		WebSocketClient client = new StandardWebSocketClient();

		WebSocketStompClient stompClient = new WebSocketStompClient(client);
		stompClient.setInboundMessageSizeLimit(64 * 1024 * 1024);
		stompClient.setMessageConverter(new MappingJackson2MessageConverter());

		WebSocketHttpHeaders wshh = new WebSocketHttpHeaders();
		wshh.set("userId", "rishantgupta007@gmail.com");

		StompSessionHandler sessionHandler = new ClientStompSessionHandler();

		ListenableFuture<StompSession> sessionAsync = stompClient.connect(URL, wshh, sessionHandler);
		StompSession stompSession = sessionAsync.get();

		// SocketClient subscribe Server Channel Push messages.
		subscribeTopics(stompSession, sessionHandler);

		// SocketClient Push messages to Server Channel.
		testSendMessage(stompSession);

		System.out.println("Please enter any key for exit...");
		new Scanner(System.in).nextLine();
	}

	private static void testSendMessage(StompSession stompSession) throws JsonProcessingException {
		Map<String, String> object = new HashMap<>();
		object.put("messageContent", "Hi I am from Java Websocket Client App 1 !!");
		sendMessage(stompSession, "/app/public/message", objectMapper.writeValueAsString(object));

		object.put("messageContent", "Hi I am from Java Websocket Client App 2 !!");
		sendMessage(stompSession, "/app/public/message", objectMapper.writeValueAsString(object));

		object.put("messageContent", "Hi I am from Java Websocket Client App 3 !!");
		sendMessage(stompSession, "/app/public/message", objectMapper.writeValueAsString(object));

		Map<String, String> object1 = new HashMap<>();
		object1.put("DECOMPRESS", "10");

		sendMessage(stompSession, "/app/private/rishantgupta007@gmail.com/message",
				objectMapper.writeValueAsString(object1));
	}

	public static void subscribeTopics(StompSession stompSession, StompSessionHandler clientTwoSessionHandler) {
		// SocketClient Subscribes topics where Server Send message on topics.
		stompSession.subscribe("/topic/public/message", clientTwoSessionHandler);
		stompSession.subscribe("/topic/private/rishant/message", clientTwoSessionHandler);
		logger.info("Client Subscribes Topics");
	}

	public static <T> void sendMessage(StompSession stompSession, String sendTo, T message) {
		// SocketClient Push messages to Server Channel.
		stompSession.send(sendTo, message);
	}
}
