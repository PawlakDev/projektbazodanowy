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

    public void deleteWorkoutById(Long workoutId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Workouts workout = session.get(Workouts.class, workoutId);
            if (workout != null) {
                session.delete(workout);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateWorkout(Workouts selectedWorkout) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(selectedWorkout);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();

        } finally {
            session.close();
        }
    }
}
