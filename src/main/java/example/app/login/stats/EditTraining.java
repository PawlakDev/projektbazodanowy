package example.app.login.stats;

import example.app.dbSettings.User;
import example.app.dbSettings.Workouts;
import org.hibernate.SessionFactory;

import javax.swing.*;
import java.util.List;

public class EditTraining
{
    JLabel headlineLabel;
    JPanel headlinePanel;
    JTable table;
    JScrollPane scrollPane;
    JButton buttonBack;
    public EditTraining(SessionFactory sessionFactory, JFrame to, User currentUser, List<Workouts> workouts, Object workoutId)
    {


    }
}
