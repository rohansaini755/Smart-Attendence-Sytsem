package com.example.geut.Pmodel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PostModel {

    public PostModel(String section) {
        this.section = section;
    }
    public PostModel(String d, String password){this.password=password;}
    public PostModel(int status1, int techId, String name) {
        this.status = status1;
        this.techId = techId;
        this.name = name;
    }
    public PostModel(int techId, String password) {
        this.techId = techId;
        this.password = password;
    }
    public PostModel(int techId)
    {
        this.techId = techId;
    }

    public PostModel(String name,int demo){this.name = name;
    this.demo = demo;}

    @SerializedName("data")
    private String data;

    @SerializedName("status")
    private int status;

    @SerializedName("techId")
    private int techId;

    @SerializedName("name")
    private String name;

    @SerializedName("password")
    private String password;

    @SerializedName("section")
    private String section;

    @SerializedName("demo")
    private int demo;




    public String getData() {
        return data;
    }

    public int getTechId() {
        return techId;
    }

    public String getName() {
        return name;
    }

    public int getStatus() {
        return status;
    }

    public String getSection() { return section; }

    public String getPassword() {return password; }

}
