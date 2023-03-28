from datetime import time

from sqlalchemy import create_engine
from sqlalchemy.dialects import mysql
from sqlalchemy.orm import sessionmaker
from sqlalchemy.ext.declarative import declarative_base
# while True:
#
#     try:
#         database = mysql.connector.connect(host="localhost", user="root", passwd="Rony147896", database="sys")
#         cursorobject = database.cursor()
#         print("connecting successfully")
#         break
#     except Exception as error:
#         print("Connecrting to database failed")
#         print("Error ", error)
#         time.sleep(2)


# 3306
URL = "mysql://root:Rony147896@localhost:3306/sys"

engine = create_engine(URL)
SessionLocal = sessionmaker(autocommit=False,autoflush=False,bind=engine)

Base = declarative_base()