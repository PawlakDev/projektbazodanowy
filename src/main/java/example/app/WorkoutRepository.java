package example.app;

import example.app.dbSettings.Workouts;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class WorkoutRepository {
    private final SessionFactory sessionFactory;

    public WorkoutRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Workouts> getWorkoutsByUserId(int userId) {
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            List<Workouts> workouts = session.createQuery(
                            "SELECT w FROM Workouts w WHERE w.idUser = :userId", Workouts.class)
                    .setParameter("userId", userId)
                    .getResultList();
            for (Workouts workout : workouts) {
                if (workout.getKilometers() == null) {
                    workout.setKilometers(0);
                }
            }
            transaction.commit();
            return workouts;
        } finally {
            session.close();
        }
    }
}
