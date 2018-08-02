import entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                                        .configure("hibernate.cfg.xml")
                                        .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Group us = session.get(Group.class, 1);

            System.out.println("Get complete: " + us);

            // commit the transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        }
        catch (Exception e) {
            if (tx!=null) tx.rollback();
            throw e;
        }
        finally {
            factory.close();
        }

    }
}
