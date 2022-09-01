from fastapi import FastAPI, WebSocket

import uvicorn

# App initialization
app = FastAPI()

@app.websocket("/ws")
async def websocket_endpoint(websocket: WebSocket):
    await websocket.accept()
    while True:
        data = await websocket.receive_text()
        await websocket.send_text(f"Message text was: {data}")

uvicorn.run(app, host="0.0.0.0", port=8282)
