package com.example.nguyenconghuong_kiemtra2_bai2;

import java.io.Serializable;

public class Khoahoc implements Serializable {
    private int id;
    private String name;
    private String date;
    private String major;
    private int active;

    public Khoahoc() {
    }

    public Khoahoc(int id, String name, String date, String major, int active) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.major = major;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
