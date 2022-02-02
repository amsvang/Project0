package com.revature.models;

public class Employee {

    private int id;
    private int userId;
    private Title title;
    private int emplNum;

    public Employee() {}

    public Employee(int id, int emplNum, int userId, Title title) {
        this.id = id;
        this.emplNum = emplNum;
        this.userId = userId;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public int getEmplNum() {
        return emplNum;
    }

    public void setEmplNum(int emplNum) {
        this.emplNum = emplNum;
    }
}
