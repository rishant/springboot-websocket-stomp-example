from fastapi import FastAPI, Request
from fastapi.templating import Jinja2Templates
from fastapi.staticfiles import StaticFiles
from fastapi.responses import HTMLResponse

import uvicorn

# App initialization
app = FastAPI()

# Templates directory, in which our html & javascript files are stored.
templates = Jinja2Templates(directory="templates")
# app.mount("/templates", StaticFiles(directory="templates", html=True), name="templates")
app.mount("/static", StaticFiles(directory="static"), name="static")

# Html Home page request.
@app.get("/", response_class=HTMLResponse)
async def home_page(request: Request):
    return templates.TemplateResponse("index.html", {"request": request})

uvicorn.run(app, host="0.0.0.0", port=8383)
