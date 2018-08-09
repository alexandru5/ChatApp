package main.java;

import main.java.entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        UserType us;
        SessionFactory factory = new Configuration()
                                        .configure("hibernate.cfg.xml")
                                        .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            us = session.get(UserType.class, 1);

            //us.print();
            //session.delete(us);
            //session.flush();
            System.out.println("Get complete: " + us);
            //us.print();

            // commit the transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        }
        catch (Exception e) {
            if (tx!=null) tx.rollback();
        }
        finally {
            factory.close();
        }

//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence-unit");
//        try {
//            EntityManager em = emf.createEntityManager();
//            em.getTransaction().begin();
//            em.persist(us);
//            em.remove(us);
//            em.getTransaction().commit();
//            em.close();
//        } finally {
//            emf.close();
//        }
    }
}
