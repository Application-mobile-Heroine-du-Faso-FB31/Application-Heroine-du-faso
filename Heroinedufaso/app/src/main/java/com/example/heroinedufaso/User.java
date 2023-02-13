package com.example.heroinedufaso;

public class User extends Person{
    private HealthHistory healthHistory;

    public HealthHistory getHealthHistory() {
        return healthHistory;
    }

    public void setHealthHistory(HealthHistory healthHistory) {
        this.healthHistory = healthHistory;
    }
}
