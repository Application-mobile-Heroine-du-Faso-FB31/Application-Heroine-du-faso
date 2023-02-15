package com.example.heroinedufaso;

import java.util.Date;

public class User extends Person{
    private HealthHistory healthHistory;


    public User(){}

    public User(String fullName, String birthday, String city, String role, String uid){
        super(fullName, birthday, city, role, uid);
    }
    public User(String fullName, String birthday, String city, String role, String uid, String phoneNumber){
        super(fullName, birthday, city, role, uid);
        this.setPhoneNumber(phoneNumber);
    }


    public HealthHistory getHealthHistory() {
        return healthHistory;
    }

    public void setHealthHistory(HealthHistory healthHistory) {
        this.healthHistory = healthHistory;
    }
}
