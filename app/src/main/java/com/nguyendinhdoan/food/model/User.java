package com.nguyendinhdoan.food.model;

public class User {

    private String name;
    private String password;

    public User() {
    }

    public User(String name, String phone) {
        this.name = name;
        this.password = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
