package com.teamphoenix.pustok_onlinebookshop.entity;

public class User {
    String _id, userName, email, phoneNumber, password;

    public User(String _id, String userName, String email, String phoneNumber, String password) {
        this._id = _id;
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public User(String userName, String email, String phoneNumber, String password) {
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public String get_id() {
        return _id;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
