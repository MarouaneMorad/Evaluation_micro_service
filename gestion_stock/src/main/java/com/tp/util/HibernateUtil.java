package com.tp.util;

import com.tp.entities.Categorie;
import com.tp.entities.Commande;
import com.tp.entities.LigneCommandeProduit;
import com.tp.entities.Produit;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    static {
        try {
            Configuration cfg = new Configuration().configure();
            cfg.addAnnotatedClass(Categorie.class);
            cfg.addAnnotatedClass(Produit.class);
            cfg.addAnnotatedClass(Commande.class);
            cfg.addAnnotatedClass(LigneCommandeProduit.class);
            StandardServiceRegistryBuilder builder =
                    new StandardServiceRegistryBuilder().applySettings(cfg.getProperties());
            sessionFactory = cfg.buildSessionFactory(builder.build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}