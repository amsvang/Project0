package com.revature.models;

import java.util.Date;

public class Users {

    private int id;
    private UserType type;
    private String first;
    private String last;
    private String email;
    private String password;
    private String ssn;
    private String username;
    private Date birthday;

    public Users() {}
    public Users(UserType type, String first, String last, String email, String password, String ssn, Date birthday){
        this.type = type;
        this.first = first;
        this.last = last;
        this.email = email;
        this.password = password;
        this.ssn = ssn;
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}