from pydantic import BaseModel


class TeacherResponse(BaseModel):
    techId: int
    name: str
    status: str = "added"

    class Config:
        orm_mode= True


class StudentResponse(BaseModel):
    URollNo: int
    CRollNo: int
    name: str

    class Config:
        orm_mode= True


class ColumnResponse(BaseModel):
    columnName: str
    status: str

    class Config:
        orm_mode= True


class AddTeacher(BaseModel):
    techId: int
    name: str


class AddStudent(BaseModel):
    URollNo: int
    CRollNo: int
    name: str


class Addsection(BaseModel):
    name: str

    class Config:
        orm_mode = True


class AddsectionResponse(BaseModel):
    name: str
    status:str

    class Config:
        orm_mode = True


class StudentLogin(BaseModel):
    UId: int
    password: int


class ResponseToStudentLogin(BaseModel):
    statuscode: int
    status: str


class ResponseToUserPage(BaseModel):
    status:int
    URollNo: int=None
    UId: int=None
    Section: str=None
    name: str=None
    CRollNo: int=None

    class Config:
        orm_mode = True


class TeacherLogin(BaseModel):
    techId: int
    password: str


class ResponseToTeacherLogin(BaseModel):
    status: int
    techId: int=None
    name: str=None

    class Config:
        orm_mode = True


class TeacherSection(BaseModel):
    techId: int

    class Config:
        orm_mode = True


class SectionPassword(BaseModel):
    name: str
    demo: int

    class Config:
        orm_mode = True


class ResponceToSectionPassword(BaseModel):
    demo: str
    password: str

    class Comfig:
        orm_mode = True


class check_section_password(BaseModel):
    Section: str
    Sec_password:str

    class Config:
        orm_mode = True

class check_password_responce(BaseModel):
    status: int

    class Config:
        orm_mode = True


class make_attendence(BaseModel):
    Section: str
    data: str
    UId: int

    class Config:
        orm_mode = True