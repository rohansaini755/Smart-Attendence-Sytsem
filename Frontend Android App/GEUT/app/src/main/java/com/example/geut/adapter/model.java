package com.example.geut.adapter;

public class model {
    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    private String sectionName;


    public model(String sectionName) {
        this.sectionName = sectionName;
    }
}
