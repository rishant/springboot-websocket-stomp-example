Publish:
- HomeController: /
- WebSocketController: /app/*
- RestApiController:  /v1/api/*

Subscribes:
- /topic/public/message
- /topic/private/{id}/message

STOMP is a frame based protocol whose frames are modeled on HTTP. The structure of a STOMP frame:

```
COMMAND
header1:value1
header2:value2

Body^@
```

Clients can use the SEND or SUBSCRIBE commands to send or subscribe for messages along with a "destination" header that describes what the message is about and who should receive it. This enables a simple publish-subscribe mechanism that can be used to send messages through the broker to other connected clients or to send messages to the server to request that some work be performed.

When using Spring’s STOMP support, the Spring WebSocket application acts as the STOMP broker to clients. Messages are routed to @Controller message-handling methods or to a simple, in-memory broker that keeps track of subscriptions and broadcasts messages to subscribed users. You can also configure Spring to work with a dedicated STOMP broker (e.g. RabbitMQ, ActiveMQ, etc) for the actual broadcasting of messages. In that case Spring maintains TCP connections to the broker, relays messages to it, and also passes messages from it down to connected WebSocket clients. Thus Spring web applications can rely on unified HTTP-based security, common validation, and a familiar programming model message-handling work.

Here is an example of a client subscribing to receive stock quotes which the server may emit periodically e.g. via a scheduled task sending messages through a SimpMessagingTemplate to the broker:

```
SUBSCRIBE
id:sub-1
destination:/topic/price.stock.*

^@
```

```
SEND
destination:/queue/trade
content-type:application/json
content-length:44

{"action":"BUY","ticker":"MMM","shares",44}^@
```

"/topic/.." implies publish-subscribe (one-to-many) message exchanges.
"/queue/" implies point-to-point (one-to-one) message exchanges.

Message — simple representation for a message including headers and payload.
MessageHandler — contract for handling a message.
MessageChannel — contract for sending a message that enables loose coupling between producers and consumers.
SubscribableChannel — MessageChannel with MessageHandler subscribers.
ExecutorSubscribableChannel — SubscribableChannel that uses an Executor for delivering messages.

"clientInboundChannel" — for passing messages received from WebSocket clients.
"clientOutboundChannel" — for sending server messages to WebSocket clients.
"brokerChannel" — for sending messages to the message broker from within server-side, application code.