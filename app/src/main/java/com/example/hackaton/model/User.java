package com.example.hackaton.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("lastname")
    @Expose
    private String lastname;

    @SerializedName("middlename")
    @Expose
    private String midname;

    @SerializedName("bith")
    @Expose
    private String bith;

    @SerializedName("sex")
    @Expose
    private String sex;


    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getMidname() {
        return midname;
    }

    public String getBith() {
        return bith;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setBith(String bith) {
        this.bith = bith;
    }

    public void setMidname(String midname) {
        this.midname = midname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setName(String name) {
        this.name = name;
    }
}
