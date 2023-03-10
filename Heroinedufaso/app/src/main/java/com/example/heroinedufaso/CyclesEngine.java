package com.example.heroinedufaso;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class CyclesEngine {

    private int calculMoyenneRegle(List jours) {
        int x = 0;
        for (int i = 0; i < jours.size(); i++) {
            x++;
        }
        return (x / jours.size());
    }

    public DurationDate calculerMenstrues(boolean cycleRegulier, List jours) {
        int duree_mentrues = 0;
        LocalDate date_debut_menstrues = LocalDate.now();
        int cycle_moyen = 28;
        int jours_debut_menstrues = 0;

        if (cycleRegulier) {
            duree_mentrues = 4;
        } else {
            duree_mentrues = ThreadLocalRandom.current().nextInt(5, 7 + 1);
        }
        if (!cycleRegulier) {
            jours_debut_menstrues = calculMoyenneRegle(jours);
            date_debut_menstrues = date_debut_menstrues.plusDays(jours_debut_menstrues);
        } else if (cycleRegulier && duree_mentrues < cycle_moyen) {
            jours_debut_menstrues = ThreadLocalRandom.current().nextInt(18, 28 + 1);
            date_debut_menstrues = date_debut_menstrues.plusDays(jours_debut_menstrues);
        } else {
            jours_debut_menstrues = ThreadLocalRandom.current().nextInt(25, 32 + 1);
            date_debut_menstrues = date_debut_menstrues.plusDays(jours_debut_menstrues);
        }

        return new DurationDate(duree_mentrues, date_debut_menstrues);
    }

}

class DurationDate {
    int duree_menstrues;
    LocalDate date_debut_menstrues;

    public DurationDate(int duree_menstrues, LocalDate date_debut_menstrues) {
       this.date_debut_menstrues = date_debut_menstrues;
       this.duree_menstrues = duree_menstrues;
    }

}
