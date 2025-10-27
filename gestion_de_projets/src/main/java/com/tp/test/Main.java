package com.tp.test;


import com.tp.entities.Employe;
import com.tp.entities.EmployeTache;
import com.tp.entities.Projet;
import com.tp.entities.Tache;
import com.tp.service.EmployeService;
import com.tp.service.EmployeTacheService;
import com.tp.service.ProjetService;
import com.tp.service.TacheService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        EmployeService employeService = new EmployeService();
        ProjetService projetService = new ProjetService();
        TacheService tacheService = new TacheService();
        EmployeTacheService etService = new EmployeTacheService();

        Employe e1 = new Employe(); e1.setNom("Ali"); e1.setPrenom("Khalid"); e1.setTelephone("0600000001");
        Employe e2 = new Employe(); e2.setNom("Sara"); e2.setPrenom("Meriem"); e2.setTelephone("0600000002");
        employeService.create(e1);
        employeService.create(e2);

        Projet p1 = new Projet();
        p1.setNom("Projet A");
        p1.setDateDebut(sdf.parse("2025-10-01"));
        p1.setDateFin(sdf.parse("2025-12-31"));
        p1.setChefProjet(e1);
        projetService.create(p1);

        Tache t1 = new Tache();
        t1.setNom("Analyse"); t1.setDateDebut(sdf.parse("2025-10-02")); t1.setDateFin(sdf.parse("2025-10-10")); t1.setPrix(1200); t1.setProjet(p1);

        Tache t2 = new Tache();
        t2.setNom("Développement"); t2.setDateDebut(sdf.parse("2025-10-11")); t2.setDateFin(sdf.parse("2025-11-30")); t2.setPrix(800); t2.setProjet(p1);

        tacheService.create(t1);
        tacheService.create(t2);

        EmployeTache et1 = new EmployeTache();
        et1.setEmploye(e1); et1.setTache(t1);
        et1.setDateDebutReelle(sdf.parse("2025-10-02")); et1.setDateFinReelle(sdf.parse("2025-10-10"));

        EmployeTache et2 = new EmployeTache();
        et2.setEmploye(e2); et2.setTache(t2);
        et2.setDateDebutReelle(sdf.parse("2025-10-11")); et2.setDateFinReelle(sdf.parse("2025-11-29"));

        etService.create(et1);
        etService.create(et2);

        List<Tache> tachesE1 = employeService.getTachesParEmploye(e1.getId());
        System.out.println("Tâches de " + e1.getNom() + ":");
        tachesE1.forEach(t -> System.out.println(t.getNom() + " - Prix: " + t.getPrix()));

        List<Projet> projetsGeres = employeService.getProjetsGeres(e1.getId());
        System.out.println("\nProjets gérés par " + e1.getNom() + ":");
        projetsGeres.forEach(p -> System.out.println(p.getNom()));

        System.out.println("\nTâches avec prix > 1000:");
        tacheService.getTachesPrixSup1000().forEach(t -> System.out.println(t.getNom() + " - Prix: " + t.getPrix()));

        Date debut = sdf.parse("2025-10-01");
        Date fin = sdf.parse("2025-11-15");
        System.out.println("\nTâches entre " + sdf.format(debut) + " et " + sdf.format(fin) + ":");
        tacheService.getTachesEntreDates(debut, fin)
                .forEach(t -> System.out.println(t.getNom() + " - " + t.getDateDebut() + " à " + t.getDateFin()));
    }
}