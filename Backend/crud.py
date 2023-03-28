from sqlalchemy.orm import Session

import model
import schemas
from model import Student, Teacher, Sections
from schemas import AddTeacher, AddStudent,AddsectionResponse
from config import engine


def get_student(db: Session):
    # return engine.execute('SELECT * FROM Student')
    return db.query(Student).all()


def get_teacher(db: Session):
    return db.query(Teacher).all()


def add_teacher(db: Session, tech: AddTeacher):
    _teacher = Teacher(techId=tech.techId, name=tech.name, password=tech.name)
    db.add(_teacher)
    db.commit()
    db.refresh(_teacher)
    return _teacher


def add_student(db:Session , std: AddStudent):
    _student = Student(URollNo=std.URollNo,CRollNo=std.CRollNo,name=std.name)
    db.add(_student)
    db.commit()
    db.refresh(_student)
    return _student



def add_column(table_name,column):
    column_name = column.compile(dialect=engine.dialect)
    column_type = column.type.compile(engine.dialect)
    engine.execute('ALTER TABLE %s ADD COLUMN %s %s' % (table_name, column_name, column_type))


def add_section(db:Session,section_name:schemas.Addsection):
    _section = Sections(name=section_name.name,password=section_name.name)
    db.add(_section)
    db.commit()
    db.refresh(_section)
    return _section


def all_section(id: int, db:Session):
    return db.query(Sections.section).filter(Sections.techId == id).all()


def check_student_id(student_login:schemas.StudentLogin,db:Session):
    _password = db.query(model.StudentLogin.password).filter(model.StudentLogin.UId == student_login.UId).first()
    if _password == None:
        return 0
    else:
        # _password_a = db.query(model.StudentLogin.password).filter(model.StudentLogin.password == student_login.password).first()
        if _password.password != student_login.password:
            return 1
        else:
            return 2


def response_to_user_page(uid: int, db: Session):
    _data = db.query(model.StudentLogin).filter(model.StudentLogin.UId == uid).first()
    return _data


def check_teacher_id(teacher_login: schemas.TeacherLogin, db:Session):
    _password = db.query(model.Teacher.password).filter(model.Teacher.techId == teacher_login.techId).first()
    if _password == None:
        return 0
    else:
        _password_a = db.query(model.Teacher.password).filter(model.Teacher.password == teacher_login.password).first()
        if _password_a == None:
            return 1
        else:
            return 2


def response_to_teacher_page(techId:int, db: Session):
    _data = db.query(model.Teacher).filter(model.Teacher.techId == techId).first()
    return _data


def getSectionPassword(name: str, db: Session):
    password = db.query(model.SectionInfo.password).filter(model.SectionInfo.name == name).first()
    return password

def checkPassword(pasw: str,sec: str, db: Session):
    passw = db.query(model.SectionInfo.password).filter(model.SectionInfo.name == sec).first()
    if passw.password == pasw:
        return 1
    else:
        return 0;


def make_attendence(section: str, col_name: str, id :id,db:Session):
    status = "p"
    roll_no = "URollNo"
    engine.execute('UPDATE %s SET %s = "p" WHERE %s = %d' % (section,col_name,roll_no,id))
    # return db.query(model.Student.a1).filter(model.Student.URollNo == 2017537).first()