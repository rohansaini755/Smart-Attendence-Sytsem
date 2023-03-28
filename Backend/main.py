from fastapi import FastAPI
import model
import router
from config import engine


model.Base.metadata.create_all(bind=engine)

app = FastAPI()


@app.get('/')
async def Home():
    return "welcome to attendence poratl"


app.include_router(router.router, prefix="/tt", tags=["tt"])

