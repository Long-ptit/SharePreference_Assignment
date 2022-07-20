package com.example.baseproject.model;

public class Account {
    private String website;
    private String userName;
    private String email;
    private String address;

    public Account(String website, String userName, String email, String address) {
        this.website = website;
        this.userName = userName;
        this.email = email;
        this.address = address;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}