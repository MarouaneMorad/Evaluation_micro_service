package com.tp.service;

import com.tp.dao.IDao;
import com.tp.entities.Employe;
import com.tp.entities.Tache;
import com.tp.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EmployeService implements IDao<Employe> {

    @Override
    public boolean create(Employe e) {
        Transaction t = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            t = session.beginTransaction();
            session.persist(e);
            t.commit();
            return true;
        } catch (Exception ex) {
            if (t != null) t.rollback();
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Employe e) {
        Transaction t = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            t = session.beginTransaction();
            session.merge(e);
            t.commit();
            return true;
        } catch (Exception ex) {
            if (t != null) t.rollback();
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Employe e) {
        Transaction t = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            t = session.beginTransaction();
            session.remove(e);
            t.commit();
            return true;
        } catch (Exception ex) {
            if (t != null) t.rollback();
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Employe findById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Employe.class, id);
        }
    }

    @Override
    public List<Employe> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Employe", Employe.class).list();
        }
    }

    // Méthodes spécifiques
    public List<Tache> getTachesParEmploye(int employeId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "select et.tache from com.tp.entities.EmployeTache et where et.employe.id=:id", Tache.class)
                    .setParameter("id", employeId)
                    .list();
        }
    }

    public List<com.tp.entities.Projet> getProjetsGeres(int employeId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "from com.tp.entities.Projet p where p.chefProjet.id=:id", com.tp.entities.Projet.class)
                    .setParameter("id", employeId)
                    .list();
        }
    }
}