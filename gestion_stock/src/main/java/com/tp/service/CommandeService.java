package com.tp.service;

import com.tp.entities.Commande;
import com.tp.dao.IDao;
import com.tp.entities.Commande;
import com.tp.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class CommandeService implements IDao<Commande> {
    private Session session;
    private Transaction transaction;

    private Session getSession() {
        return HibernateUtil.getSessionFactory().getCurrentSession(); // ✅
    }

    @Override
    public void create(Commande o) {
        session = getSession();
        transaction = session.beginTransaction();
        session.save(o);
        transaction.commit();
    }

    @Override
    public void update(Commande o) {
        session = getSession();
        transaction = session.beginTransaction();
        session.update(o);
        transaction.commit();
    }

    @Override
    public void delete(Commande o) {
        session = getSession();
        transaction = session.beginTransaction();
        session.delete(o);
        transaction.commit();
    }

    @Override
    public Commande findById(int id) {
        session = getSession();
        transaction = session.beginTransaction();
        Commande c = session.get(Commande.class, id);
        transaction.commit();
        return c;
    }

    @Override
    public List<Commande> findAll() {
        session = getSession();
        transaction = session.beginTransaction();
        List<Commande> commandes = session.createQuery("from Commande", Commande.class).list();
        transaction.commit();
        return commandes;
    }
}