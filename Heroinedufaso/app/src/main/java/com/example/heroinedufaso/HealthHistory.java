package com.example.heroinedufaso;

import java.util.List;

public class HealthHistory {
    private List<String> currentMedication;
    private List<String> allergies;
    private List<String> familyHistory;
    private String bloodGroup;


    public HealthHistory(List<String> currentMedication, List<String> allergies, List<String> familyHistory, String bloodGroup) {
        this.currentMedication = currentMedication;
        this.allergies = allergies;
        this.familyHistory = familyHistory;
        this.bloodGroup = bloodGroup;
    }


    public List<String> getCurrentMedication() {
        return currentMedication;
    }

    public void setCurrentMedication(List<String> currentMedication) {
        this.currentMedication = currentMedication;
    }

    public List<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }

    public List<String> getFamilyHistory() {
        return familyHistory;
    }

    public void setFamilyHistory(List<String> familyHistory) {
        this.familyHistory = familyHistory;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }
}
