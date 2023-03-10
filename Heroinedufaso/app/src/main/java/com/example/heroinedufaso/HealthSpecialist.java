package com.example.heroinedufaso;

public class HealthSpecialist extends User {
    private String specialisation;
    private String emailAddress;

    public HealthSpecialist(){}

    public HealthSpecialist(String birthday, String city, String fullName, String phoneNumber, String role, String uid){
        super(birthday, city, fullName, phoneNumber, role, uid);
    }
    public HealthSpecialist(String birthday, String city, String email, String fullName, String phoneNumber, String photoProfileURL,
                            String role, String specialisation, String uid){
        super(birthday, city, fullName, phoneNumber, photoProfileURL, role, uid);
        this.specialisation = specialisation;
        emailAddress = email;
    }

    public HealthSpecialist(String birthday, String city, String fullName, String phoneNumber, String role, String specialisation,
                            String uid){
        super(birthday, city, fullName, phoneNumber, role, uid);
        this.specialisation = specialisation;
    }

    public HealthSpecialist(String birthday, String city, String email, String fullName, String phoneNumber, String role, String specialisation,
                            String uid){
        super(birthday, city, fullName, phoneNumber, role, uid);
        this.specialisation = specialisation;
        emailAddress = email;
    }


    public String getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
