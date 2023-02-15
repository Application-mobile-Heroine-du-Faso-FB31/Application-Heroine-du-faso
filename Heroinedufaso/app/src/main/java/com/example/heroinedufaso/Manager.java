package com.example.heroinedufaso;

import java.util.Date;

public class Manager extends Person{

    private String email;

    public Manager(){}

    public Manager(String fullName, String birthday, String city, String role, String uid){
        super(fullName, birthday, city, role, uid);
    }

    public Manager(String fullName, String birthday, String city, String role, String uid, String email){
        super(fullName, birthday, city, role, uid);
        this.email = email;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
