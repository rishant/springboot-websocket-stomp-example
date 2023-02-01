## Step : 1 - NodeJS project creation
```
npm init
```


> ## Step : 2 - Added [Production] dependencies for Express {Web Framework} & Socket.io {Websocket}
> ```
> npm install express socket.io
> ```


> ## Step : 3 - Added [Production] dependencies for Moment {date-parsing}
> ```
> npm install moment
> ```


> ## Step : 4 - Added [Development] dependencies for nodemon {Auto Start on Change}
> ```
> npm install -D nodemon
> ```


> ## Step : 5 - Added server start script in package.json
> ```
> "scripts": {
>    "start": "node server.js",
>    "dev": "nodemon server.js"
>  }
> ```

> ## Step : 6 - Start NodeJS Server
> ```
> npm run dev
> ```

## Step : 7 - Added WebSocket[Socket.io] dependency on NodeJS Server
> ```
> npm install socket.io
> ```


| Send Client event to Server | Take Client event from Server |
| -------------------- | ---------------------- |
| socket.emit          | socket.on |

| Send Server Event to Client | Take Event from Client |
| --------------- | ------------- |
| io.emit <br/> io.to(socketId).emit | socket.on  |

https://www.youtube.com/watch?v=jD7FnbI76Hg
https://www.youtube.com/watch?v=otaQKODEUFs&t=13221s
