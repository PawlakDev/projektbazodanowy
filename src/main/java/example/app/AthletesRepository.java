package example.app;

import example.app.dbSettings.Athlete;
import example.app.dbSettings.Workouts;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class AthletesRepository {
    private SessionFactory sessionFactory;

    public AthletesRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Athlete> getAthletesByUserId(int userId) {
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            List<Athlete> athletes = session.createQuery(
                            "SELECT a FROM Athlete a WHERE a.uid = :userId", Athlete.class)
                    .setParameter("uid", userId)
                    .getResultList();
            transaction.commit();
            return athletes;
        } finally {
            session.close();
        }
    }
}
