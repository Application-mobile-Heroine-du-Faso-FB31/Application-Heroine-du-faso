package com.example.heroinedufaso;

import java.time.LocalDate;
import java.util.Date;

public class Person {
    private String fullName;
    private String birthday;
    private String phoneNumber;
    private String city;
    private String role;

    private String uid;

    public Person(){}


    public Person(String fullName, String birthday, String city, String role, String uid) {
        this.fullName = fullName;
        this.birthday = birthday;
        this.city = city;
        this.role = role;
        this.uid = uid;
    }



    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String toString(){
        return "nom complet : " + fullName + " id : " + uid;
    }

}
