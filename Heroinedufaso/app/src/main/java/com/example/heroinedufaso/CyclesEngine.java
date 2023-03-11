package com.example.heroinedufaso;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class CyclesEngine {
    private int bloodDuration;
    private LocalDate dayOfStartOfTheLastPeriod;
    private int cycleDuration;
    private LocalDate dayOfOvulation;
    private final LocalDate [] fertilizedPeriod = new LocalDate[5];
    private LocalDate dayOfStartOfTheNextPeriod;
    private ArrayList<LocalDate> bloodPeriod;

    private final int CYCLEDURATION = 28;

    public CyclesEngine(){
        bloodPeriod = new ArrayList<>();
    }

    public CyclesEngine(int bloodDuration, LocalDate dayOfStartOfTheLastPeriod, int cycleDuration) {
        this.bloodDuration = bloodDuration;
        this.dayOfStartOfTheLastPeriod = dayOfStartOfTheLastPeriod;
        this.cycleDuration = cycleDuration;
        bloodPeriod = new ArrayList<>();
        setDayOfOvulation();
        setBloodPeriodDays();
        setFertilizedPeriodDays();
        setDayOfStartOfTheNextPeriod();
    }


    public int getCycleDuration() {
        return cycleDuration;
    }

    public void setCycleDuration() {
        this.cycleDuration = cycleDuration;
    }

    public LocalDate getDayOfStartOfTheLastPeriod() {
        return dayOfStartOfTheLastPeriod;
    }

    public void setDayOfStartOfTheLastPeriod(LocalDate dayOfStartOfTheLastPeriod) {
        this.dayOfStartOfTheLastPeriod = dayOfStartOfTheLastPeriod;
    }

    public int getBloodDuration() {
        return bloodDuration;
    }

    public void setPeriodDuration(int bloodDuration) {
        this.bloodDuration = bloodDuration;
    }

    public LocalDate getDayOfOvulation() {
        return dayOfOvulation;
    }

    public void setDayOfOvulation() {
        this.dayOfOvulation = dayOfStartOfTheLastPeriod.plusDays(cycleDuration - 14);
    }

    public LocalDate[] getFertilizedPeriod() {
        return fertilizedPeriod;
    }

    public void setFertilizedPeriodDays(){
        fertilizedPeriod[0] = dayOfOvulation.minusDays(3);
        fertilizedPeriod[1] = dayOfOvulation.minusDays(2);
        fertilizedPeriod[2] = dayOfOvulation.minusDays(1);
        fertilizedPeriod[3] = dayOfOvulation;
        fertilizedPeriod[4] = dayOfOvulation.plusDays(1);
    }

    public LocalDate getDayOfStartOfTheNextPeriod() {
        return dayOfStartOfTheNextPeriod;
    }

    public void setDayOfStartOfTheNextPeriod() {
        this.dayOfStartOfTheNextPeriod = dayOfStartOfTheLastPeriod.plusDays(cycleDuration);
    }

    public ArrayList<LocalDate> getBloodPeriod() {
        return bloodPeriod;
    }

    public void setBloodPeriodDays() {
        for(int i = 0; i < bloodDuration; i++){
            bloodPeriod.add(dayOfStartOfTheLastPeriod.plusDays(i));
        }
    }

    public String toString(){
        return "La date du de debut du cycle est : " + dayOfStartOfTheLastPeriod.toString() +
                "\n La duree du cycle est : " + cycleDuration +
                "\n La duree du saignement est : " + bloodDuration +
                "\n La date du debut de saignement est : " + bloodPeriod.get(0).toString() +
                "\n La date de la fin du saignement est : " + bloodPeriod.get(bloodPeriod.size() - 1).toString() +
                "\n La date d'ovulation est : " + dayOfOvulation.toString() +
                "\n La periode de fertilité est : " + fertilizedPeriod[0].toString() + " à " + fertilizedPeriod[4] +
                "\n La date du prochain saignement est : " + dayOfStartOfTheNextPeriod.toString();
    }
}

