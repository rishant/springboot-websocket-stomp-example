# springboot-websocket-stomp-example
Springboot sample code for 2 way communicate via WebSocket over STOMP (Pub/Sub) subprotocol.

# Technical Words in [Messaging System]:
```
> 1. Producer 			:: Send Message [Reliblity & Ensure Delivery]\
> 2. Broker   			:: Hold Message [Durablity\, Interoperability & Security](JMS, ActiveMQ, RabbitMQ, SpringBoot-WebSockets, Kafka, Redis(Pub/Sub), ably) \
> 3. Subscriber 		:: Consume Message [Highly Scalable & Message Ordering] \
> 4. Source Connector	:: Read data from 3rd party system and ingress/reach in to the Broker. \
> 5. Sink Connector		:: Read data from Broker and Sink to 3rd party system. \
> 6. Message Schema		:: Message Schema is the Data input/output format for Broker system. \
> 7. Schema Registry	:: Schema Registry is place where Message Schema is stored and Broker will interact to serialize/de-serialize Message.
```

# Video References:
[![SC2 Video](https://img.youtube.com/vi/o_IjEDAuo8Y/0.jpg)](https://www.youtube.com/watch?v=o_IjEDAuo8Y)
[![SC2 Video](https://img.youtube.com/vi/XY5CUuE6VOk/0.jpg)](https://www.youtube.com/watch?v=XY5CUuE6VOk&list=PLXy8DQl3058PNFvxOgb5k52Det1DGLWBW)

[![SC2 Video](https://img.youtube.com/vi/Mm6_DlO5vvs/0.jpg)](https://www.youtube.com/watch?v=Mm6_DlO5vvs)
[![SC2 Video](https://img.youtube.com/vi/UBFs84OQrko/0.jpg)](https://www.youtube.com/watch?v=UBFs84OQrko&list=PLStdkmnF3FJVW-Xat9K4i4SqvXT4E7I2g&index=1)
[![SC2 Video](https://img.youtube.com/vi/d2_bcbUbCtc/0.jpg)](https://www.youtube.com/watch?v=d2_bcbUbCtc&t=16535s)

# From Zero to Hero with Spring WebSocket:
> https://www.infoq.com/presentations/spring-websocket/