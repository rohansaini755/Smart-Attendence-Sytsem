from config import Base
from sqlalchemy import Column,Integer,String


class StudentLogin(Base):
    __tablename__ = "studentlogin"

    URollNo = Column(Integer,primary_key=True,nullable=False)
    UId = Column(Integer,primary_key=True,nullable=False)
    Section = Column(String(10),unique=True,nullable=False)
    name = Column(String(20),nullable=False)
    CRollNo = Column(Integer,primary_key=True,nullable=False)
    password = Column(Integer,nullable=False)


class Student(Base):
    __tablename__ = "student"

    URollNo = Column(Integer,primary_key=True)
    CRollNo = Column(Integer,primary_key=True)
    name = Column(String(20),nullable=False)


class Teacher(Base):
    __tablename__ = "teacher"

    techId = Column(Integer,primary_key=True)
    name = Column(String(20),nullable=False)
    password = Column(String(20),nullable=False)


class Sections(Base):
    __tablename__ = "trs"

    techId = Column(Integer, nullable=False)
    section =Column(String(20), primary_key=True, nullable=False)
    # password = Column(String(10),nullable=False)


class SectionInfo(Base):
    __tablename__ = "secpass"

    name = Column(String(20),primary_key=True)
    password = Column(String(10),nullable=False)

















    # def add_column(engine, table_name, column):
    #     column_name = column.compile(dialect=engine.dialect)
    #     column_type = column.type.compile(engine.dialect)
    #     engine.execute('ALTER TABLE %s ADD COLUMN %s %s' % (table_name, column_name, column_type))
    #
    # column = Column('new_column_name', String(100), primary_key=True)
    # add_column(engine, table_name, column)
