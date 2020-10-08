package com.achs.entity;

/**
 * Created by IntelliJ IDEA.
 * User: abhinacharya
 * Date: 10/3/20
 * Time: 7:13 PM
 */
public class User {
    private int uId;
    private String userName;
    private String email;
    private String password;
    private String address;
    private String contactNumber;

    public User() {
    }

    public int setUid(int uId){
        this.uId=uId;
        return uId;
    }

    public int getUid() {
        return uId;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public User(int uId, String userName, String email, String password, String address, String contactNumber) {
        this.uId = uId;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.contactNumber = contactNumber;
    }
}
