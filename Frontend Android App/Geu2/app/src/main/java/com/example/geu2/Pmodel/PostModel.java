package com.example.geu2.Pmodel;

import com.google.gson.annotations.SerializedName;

public class PostModel {


    public PostModel(int UId, int password) {
        this.UId = UId;
        this.password = password;
    }

    public PostModel(String data) {
        this.data = data;
    }

    public PostModel(int status) {
        this.status = status;
    }

    public PostModel(int status, int URollNo, int UId, String section, String name, int CRollNo) {
        this.status = status;
        this.URollNo = URollNo;
        this.UId = UId;
        Section = section;
        this.name = name;
        this.CRollNo = CRollNo;
    }

    public PostModel(String section,String password) {
        this.Sec_password= password;
        this.Section = section;
    }

    public PostModel(String section , String col, int id)
    {
        this.Section = section;
        this.data = col;
        this.UId = id;
    }




    @SerializedName("status")
    private int status;
    @SerializedName("data")
    private String data;
    @SerializedName("URollNo")
    private int URollNo;
    @SerializedName("UId")
    private int UId;
    @SerializedName("password")
    private int password;
    @SerializedName("Sec_password")
    private String Sec_password;
    @SerializedName("Section")
    private String Section;
    @SerializedName("name")
    private String name;
    @SerializedName("CRollNo")
    private int CRollNo;



    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getURollNo() {
        return URollNo;
    }

    public void setURollNo(int URollNo) {
        this.URollNo = URollNo;
    }

    public int getUId() {
        return UId;
    }

    public void setUId(int UId) {
        this.UId = UId;
    }

    public String getSection() {
        return Section;
    }

    public void setSection(String section) {
        Section = section;
    }

    public String getName() {
        return name;
    }

    public String getData() {
        return data;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCRollNo() {
        return CRollNo;
    }

    public void setCRollNo(int CRollNo) {
        this.CRollNo = CRollNo;
    }



//    public PostModel(int status, int URollNo, int UId, String section, int name, int CRollNo) {
//        this.status = status;
//        this.URollNo = URollNo;
//        this.UId = UId;
//        Section = section;
//        this.name = name;
//        this.CRollNo = CRollNo;
//    }
//    public PostModel(int UId, int password) {
//        this.UId = UId;
//        this.password = password;
//    }

//    @SerializedName("status")
//    private int status;
//    @SerializedName("URollNo")
//    private int URollNo;
//    @SerializedName("UId")
//    private int UId;

//    public int getStatus() {
//        return status;
//    }
//
//    public void setStatus(int status) {
//        this.status = status;
//    }
//
//    public int getURollNo() {
//        return URollNo;
//    }
//
//    public void setURollNo(int URollNo) {
//        this.URollNo = URollNo;
//    }
//
//    public String getSection() {
//        return Section;
//    }
//
//    public void setSection(String section) {
//        Section = section;
//    }
//
//    public int getName() {
//        return name;
//    }
//
//    public void setName(int name) {
//        this.name = name;
//    }
//
//    public int getCRollNo() {
//        return CRollNo;
//    }
//
//    public void setCRollNo(int CRollNo) {
//        this.CRollNo = CRollNo;
//    }
//
//    public void setData(String data) {
//        this.data = data;
//    }
//
//    @SerializedName("Section")
//    private String Section;
//    @SerializedName("name")
//    private int name;
//    @SerializedName("CRollNo")
//    private int CRollNo;



//    public int getUId() {
//        return UId;
//    }
//
//    public void setUId(int UId) {
//        this.UId = UId;
//    }
//
//    public int getPassword() {
//        return password;
//    }
//
//    public void setPassword(int password) {
//        this.password = password;
//    }
//
//    @SerializedName("password")
//    private int password;


//    @SerializedName("UserName")
//    private String UserName;
//    @SerializedName("Email")
//    private String Email;
//    @SerializedName("LoginProvider")
//    private String LoginProvider;
//    @SerializedName("status")
//    private int status;
//
//    public PostModel(String userName, String email, String loginProvider) {
//        UserName = userName;
//        Email = email;
//        LoginProvider = loginProvider;
//    }
//
//    public int getStatus()
//    {
//        return status;
//    }
//
//    public void setStatus(int s){
//        status =s;
//    }
//
//    public String getUserName() {
//        return UserName;
//    }
//
//    public void setUserName(String userName) {
//        UserName = userName;
//    }
//
//    public String getEmail() {
//        return Email;
//    }
//
//    public void setEmail(String email) {
//        Email = email;
//    }
//
//    public String getLoginProvider() {
//        return LoginProvider;
//    }
//
//    public void setLoginProvider(String loginProvider) {
//        LoginProvider = loginProvider;
//    }
}
