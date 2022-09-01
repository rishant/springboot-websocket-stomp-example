// Python websocket Client
var ws = new WebSocket("ws://localhost:8282/ws");

// SockJS SpringBoot Websocket Client.
var springBootWebSocket = new SockJS('http://localhost:8181/ws-socket');
// Stomp client over SockJS Websocket.
var stompClient = Stomp.over(springBootWebSocket);

stompClient.connect({}, function (frame) {
    console.log('Connected: ' + frame);
    stompClient.subscribe("/topic/public/message", function (greeting) {
        console.log(greeting.body);
        var messages = document.getElementById('messages')
        var message = document.createElement('li')
        var content = document.createTextNode(greeting.body)
        message.appendChild(content)
        messages.appendChild(message)
    });
});


ws.onmessage = function(event) {
    console.log(event.data)
    var messages = document.getElementById('messages')
    var message = document.createElement('li')
    var content = document.createTextNode(event.data)
    message.appendChild(content)
    messages.appendChild(message)
};

function sendMessage(event) {
    console.log(event)
    var input = document.getElementById("messageText")

    // python websocket Push message.
    ws.send(input.value)

    // java springboot STOMP oer Websocket Push message.
    stompClient.send("/app/public/message", {}, JSON.stringify({'message': input.value}));

    input.value = ''
    event.preventDefault()
}