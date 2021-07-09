package com.example.e_l;

public class NewUserClass {
    String fullName, userName, email, password, phoneNo;

    public NewUserClass(){

    }

    public NewUserClass(String fullName, String userName, String email, String password, String phoneNo) {
        this.fullName = fullName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.phoneNo = phoneNo;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
