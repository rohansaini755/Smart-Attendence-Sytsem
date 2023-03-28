from fastapi import APIRouter,Depends
from sqlalchemy import Column,String
import schemas
from config import SessionLocal
from sqlalchemy.orm import Session
import crud
from schemas import TeacherResponse,AddsectionResponse
from typing import List

router = APIRouter()


def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()


@router.get('/check')
def checking(db:Session = Depends(get_db)):
    return {"data" : "well allthing is good 2"}


@router.get('/allteacher',response_model=List[TeacherResponse])
def allteacher(db:Session = Depends(get_db)):
    _teacher = crud.get_teacher(db=db);
    return _teacher


@router.post('/addteacher',response_model=TeacherResponse)
def addteacher(tech:schemas.AddTeacher, db:Session = Depends(get_db)):
    _teacher = crud.add_teacher(db,tech)
    print(_teacher)
    return _teacher


@router.post('/addstudent',response_model=schemas.StudentResponse)
def addstudent(tech:schemas.AddStudent,db: Session = Depends(get_db)):
    _student = crud.add_student(db,tech)
    return _student


@router.get('/allstudent',response_model=List[schemas.StudentResponse])
def allstudent(db:Session = Depends(get_db)):
    _student = crud.get_student(db)
    return _student



@router.post('/addcolumn')
def addcolumn(db:Session = Depends(get_db)):
    column = Column('a1', String(100))
    crud.add_column("student", column)
    return {"status": "column has been successfully added"}


@router.post('/addsection')
def addsection(section_name:schemas.Addsection,db:Session = Depends(get_db)):
    _section = crud.add_section(db, section_name)
    return schemas.AddsectionResponse(name=_section.name, status="section successfully added")


# @router.get('/allsection',response_model=List[schemas.Addsection])
# def allsection(db:Session = Depends(get_db)):
#     _sections = crud.all_section(db)
#     return _sections


@router.post('/allsection')
def allsection(tech_id: schemas.TeacherSection, db: Session = Depends(get_db)):
    id = tech_id.techId
    _sections = crud.all_section(id,db)
    # return {"data" : _sections}
    return _sections

# @router.post('/checkstudentlogin')
# def check_student_login(student_login: schemas.StudentLogin, db: Session = Depends(get_db)):
#     return {"data":"has been verified"}


@router.post('/checkstudentlogin')
def check_student_login(student_login: schemas.StudentLogin, db: Session = Depends(get_db)):
    status = crud.check_student_id(student_login, db)
    if status == 0:
        # return {"data" : "invalid id"}
        return schemas.ResponseToUserPage(status=0)
        # return {"data":schemas.ResponseToUserPage(status=0)}
    if status == 1:
        # return {"data":"invalid password"}
        return schemas.ResponseToUserPage(status=1)
        # return {"status":schemas.ResponseToUserPage(status=1)}
    if status == 2:
        # return {"data" : "has been varified"}
        _data = crud.response_to_user_page(student_login.UId, db)
        return schemas.ResponseToUserPage(status=2,URollNo=_data.URollNo, UId=_data.UId, Section=_data.Section, name=_data.name,CRollNo=_data.CRollNo)

        # return {"data":schemas.ResponseToUserPage(status=2,URollNo=_data.URollNo, UId=_data.UId, Section=_data.Section, name=_data.name,CRollNo=_data.CRollNo)}



# @router.get('/{id}')
# def check_student(id:String,db : Session = Depends(get_db) ):
#     return {"data" : "done"}


@router.post('/checkteacherlogin')
def check_techer_login(teacher_login: schemas.TeacherLogin, db: Session = Depends(get_db)):
    status = crud.check_teacher_id(teacher_login, db)
    if status == 0:
        return schemas.ResponseToTeacherLogin(status=0)
    elif status == 1:
        return schemas.ResponseToTeacherLogin(status=1)
    elif status == 2:
        _data = crud.response_to_teacher_page(teacher_login.techId,db)
        return schemas.ResponseToTeacherLogin(status=2,techId=_data.techId,name=_data.name)


# @router.get('/getsection/{id}/')
# def get_section(id:int ,db: Session = Depends(get_db)):

@router.get('/experiment')
def experiment(db: Session = Depends(get_db)):
    data = {"rohan","saini"}
    return {"data" : data}

@router.post('/generateqr')
def generateqr(section: schemas.SectionPassword, db: Session = Depends(get_db)):
    id = section.name;
    _password = crud.getSectionPassword(id,db)
    # return section.name;
    return schemas.SectionPassword(name=_password.password,demo=1)


@router.post('/checkPassword')
def checkPassword(password: schemas.check_section_password, db: Session = Depends(get_db)):
    pas = password.Sec_password;
    sec = password.Section;
    status = crud.checkPassword(pas,sec,db);
    return schemas.check_password_responce(status=status)
    # return status


@router.post('/makeattendence')
def makeattendence(info: schemas.make_attendence, db: Session = Depends(get_db)):
    section = info.Section
    col_name = info.data
    id = info.UId
    status = crud.make_attendence(section,col_name,id,db)
    return status

