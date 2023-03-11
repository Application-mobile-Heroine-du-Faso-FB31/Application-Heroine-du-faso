package com.example.heroinedufaso;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class CyclesEngine {

    private LocalDate dateDuDernierSaignement;
    private int dureeDuSaignement;
    private LocalDate dateDebutMenstrues;
    private final int DUREEDUCYCLEPARDEFAUT = 28;
    private int dureeDuCycle;
    private ArrayList<Integer> listeDeDureeDeCyclePrecedent;
    private LocalDate dateOvulation;
    private  LocalDate [] periodeFertilite;


    public CyclesEngine(){
    }



    private void calculMoyenneRegle() {
        int sumOfDays = 0;
        for (int i = 0; i < listeDeDureeDeCyclePrecedent.size(); i++) {
            sumOfDays = sumOfDays + listeDeDureeDeCyclePrecedent.get(i);
        }
        this.setDureeDuCycle(Math.round(sumOfDays/listeDeDureeDeCyclePrecedent.size()));
    }




    public LocalDate getDateDuDernierSaignement() {
        return dateDuDernierSaignement;
    }

    public int getDureeDuSaignement() {
        return dureeDuSaignement;
    }

    public void setDureeDuSaignement(int dureeDuSaignement) {
        this.dureeDuSaignement = dureeDuSaignement;
    }

    public void setDateDuDernierSaignement(LocalDate dateDuDernierSaignement) {
        this.dateDuDernierSaignement = dateDuDernierSaignement;
    }

    public LocalDate getDatDebutMenstrues() {
        return dateDebutMenstrues;
    }

    public void setDatDebutMenstrues(LocalDate datDebutMenstrues) {
        this.dateDebutMenstrues = datDebutMenstrues;
    }

    public int getDureeDuCycle() {
        return dureeDuCycle;
    }

    public void setDureeDuCycle(int dureeDuCycle) {
        this.dureeDuCycle = dureeDuCycle;
    }

    public LocalDate getDateOvulation() {
        return dateOvulation;
    }

    public void setDateOvulation(){
        this.dateDebutMenstrues = dateDebutMenstrues.plusDays(dureeDuCycle - 14);
    }

}

