const express = require('express');
const http = require('http');
const socketio = require('socket.io');
// const cors = require('cors');

const app = express();
const server = http.createServer(app);
const io = socketio(server);
// const io = socketio(server, {
//     cors : {
//         origin: "*",
//         methods: ["GET", "POST"]
//     }
// });

// Run when client connects
io.on('connection', (socket) => {
    // Welcome current user
    socket.emit('message', "Welecome to Chat Server.");

    // Broadcast when a user connects
    socket.broadcast('message', 'A user has joined the chat server');

    // Runs when client disconnects
    socket.on('disconnect', () => {
        io.emit('message', 'A user has joined the chat server');
    });

    // Listen for chatMessage Channel.
    socket.on('chatMessage', function (message) {
        console.log(message);
        // Update to Clients Who Subscribers chatMessage.
        io.emit('chatMessage', message);
    });

});


const PORT = 3000 || process.env.PORT;

server.listen(PORT, () => {console.log(`Server running on port ${PORT}`)});