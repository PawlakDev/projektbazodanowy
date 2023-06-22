package example.app;

import example.app.dbSettings.Athlete;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class AthletesRepository {
    private final SessionFactory sessionFactory;

    public AthletesRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    // method for searching for specific user by id
    public List<Athlete> getAthletesByUserId(int userId) {
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            List<Athlete> athletes = session.createQuery(
                            "SELECT a FROM Athlete a WHERE a.uid = :userId", Athlete.class)
                    .setParameter("userId", userId)
                    .getResultList();
            transaction.commit();
            return athletes;
        } finally {
            session.close();
        }
    }

    // methopd for listing all of the users
    public List<Athlete> getAllAthletes() {
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            List<Athlete> athletes = session.createQuery("SELECT a FROM Athlete a", Athlete.class)
                    .getResultList();
            transaction.commit();
            return athletes;
        } finally {
            session.close();
        }
    }


}
