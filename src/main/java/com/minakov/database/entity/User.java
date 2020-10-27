package com.minakov.database.entity;
/**
 * This class describes a user form DB.
 *
 * @author Serhii Minakov
 */
public class User {
    private int id;
    private String firstName;
    private String secondName;
    private String email;
    private String phoneNumber;
    private String address;
    private Status type;
    private String password;


    public User(int id, Status type, String password, String firstName,
                String secondName, String phoneNumber, String address, String email) {
        this.id = id;
        this.type = type;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public User(int id, Status type, String password,
                String firstName, String secondName, String phoneNumber, String address) {
        this.id = id;
        this.type = type;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
    public Status getType() {
        return type;
    }

    public void setType(Status type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

//    @Override
//    public String toString() {
//        return this.firstName==null?"null":this.firstName;
//    }
}
