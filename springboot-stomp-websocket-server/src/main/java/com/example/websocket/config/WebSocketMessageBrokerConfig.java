package com.example.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSocketMessageBroker
@Slf4j
public class WebSocketMessageBrokerConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		// without sockjs
		registry.addEndpoint("/ws-socket").setAllowedOriginPatterns("*");
		// Intercepter for request Authentication.
//		registry.addEndpoint("/ws-socket").addInterceptors(null).setAllowedOriginPatterns("*");
		
		// with sockjs
		registry.addEndpoint("/ws-socket").setAllowedOriginPatterns("*").withSockJS();
		// Intercepter for request Authentication.
//		registry.addEndpoint("/ws-socket").addInterceptors(null).setAllowedOriginPatterns("*").withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		/* "/app/foo.bar.baz123" */
		//registry.setPathMatcher(new AntPathMatcher("."));
		
		// MessageBroker Routing key's
		registry.enableSimpleBroker("/queue/", "/topic/");
		
		// WebSocket Controller handler routing key's
		registry.setApplicationDestinationPrefixes("/app");
	}

	@Override
	public void configureWebSocketTransport(WebSocketTransportRegistration registry) {
		registry.setSendTimeLimit(15 * 1000);
		registry.setSendBufferSizeLimit(512 * 1024);
		
		/*
		 * Spring’s STOMP over WebSocket support does this so applications can configure
		 * the maximum size for STOMP messages irrespective of WebSocket server specific
		 * message sizes.
		 */
		registry.setMessageSizeLimit(128 * 1024);
	}
	
	@Override
	public void configureClientInboundChannel(ChannelRegistration registration) {
		WebSocketMessageBrokerConfigurer.super.configureClientInboundChannel(registration);
	}
	
	@Override
	public void configureClientOutboundChannel(ChannelRegistration registration) {
		WebSocketMessageBrokerConfigurer.super.configureClientOutboundChannel(registration);
	}
}
