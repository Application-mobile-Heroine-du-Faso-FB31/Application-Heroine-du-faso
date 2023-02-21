package com.example.heroinedufaso;

public class User {

    private String birthday;
    private String city;
    private String fullName;

    private HealthHistory healthHistory;
    private String phoneNumber;
    private String photoProfileURL;
    private String role;

    private String uid;

    public User(){}



    public User(String birthday, String city, String fullName, String phoneNumber, String role, String uid){
        this.fullName = fullName;
        this.birthday = birthday;
        this.city = city;
        this.phoneNumber = phoneNumber;
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

    public HealthHistory getHealthHistory() {
        return healthHistory;
    }

    public void setHealthHistory(HealthHistory healthHistory) {
        this.healthHistory = healthHistory;
    }

    public String getPhotoProfileURL() {
        return photoProfileURL;
    }

    public void setPhotoProfileURL(String photoProfileURL) {
        this.photoProfileURL = photoProfileURL;
    }
}
