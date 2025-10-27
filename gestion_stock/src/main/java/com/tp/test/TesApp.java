package com.tp.test;

import com.tp.entities.Categorie;
import com.tp.entities.Commande;
import com.tp.entities.LigneCommandeProduit;
import com.tp.entities.Produit;
import com.tp.service.CategorieService;
import com.tp.service.CommandeService;
import com.tp.service.LigneCommandeService;
import com.tp.service.ProduitService;

import java.util.Date;

public class TesApp {
    public static void main(String[] args) {
        CategorieService catService = new CategorieService();
        ProduitService produitService = new ProduitService();
        CommandeService cmdService = new CommandeService();
        LigneCommandeService ligneService = new LigneCommandeService();

        Categorie cat = new Categorie("INF", "Informatique");
        catService.create(cat);

        Produit p1 = new Produit("ES12", 120, cat);
        Produit p2 = new Produit("ZR85", 100, cat);
        Produit p3 = new Produit("EE85", 200, cat);
        produitService.create(p1);
        produitService.create(p2);
        produitService.create(p3);

        Commande cmd = new Commande(new Date());
        cmdService.create(cmd);

        ligneService.create(new LigneCommandeProduit(p1, cmd, 7));
        ligneService.create(new LigneCommandeProduit(p2, cmd, 14));
        ligneService.create(new LigneCommandeProduit(p3, cmd, 5));

        System.out.println("\nProduits commandés dans la commande " + cmd.getId() + ":");
        produitService.findProduitsByCommande(cmd).forEach(obj ->
                System.out.println(obj[0] + "\t" + obj[1] + " DH\t" + obj[2])
        );
    }
}
