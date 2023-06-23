package example.app.login;
import example.app.dbSettings.User;
import example.app.dbSettings.Workouts;

import org.hibernate.SessionFactory;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import example.app.WorkoutRepository;

//musze dokończyć

public class EditTraining extends JFrame {
    JLabel headlineLabel;
    JPanel headlinePanel;
    JTable table;
    JScrollPane scrollPane;
    JButton buttonBack;

    public EditTraining(SessionFactory sessionFactory, JFrame to, User currentUser, List<Workouts> workouts, int workoutId) {
        WorkoutRepository repository = new WorkoutRepository(sessionFactory);
        List<Workouts> userWorkouts = repository.getWorkoutsByUserId(currentUser.getId());

        // dane do tabeli przchowuja dane o treningach
        final Object[][][] tableData = {new Object[userWorkouts.size()][]};

        for (int i = 0; i < userWorkouts.size(); i++) {
            Workouts workout = userWorkouts.get(i);
            if (workout.getId() == workoutId) {
                Object[] rowData = {
                        workout.getId(),
                        workout.getDate(),
                        workout.getWorkouttype(),
                        workout.getKilometers(),
                        workout.getTimeworkout(),
                        workout.getDescription()
                };
                tableData[0][1] = rowData;
            }
        }
        // nazwy kolumn
        String[] columnNames = {"ID", "Date", "Workout Type", "Kilometers", "Time (mins)", "Opis"};

        // tworze z tego tabele
        table = new JTable(tableData[0], columnNames);

        // ustawienia tabeli
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
//        table.setModel(new ShowTrainings.MyTableModel(tableData[0], columnNames));


        // ustawiam headline
        headlineLabel = new JLabel();
        headlinePanel = new JPanel();
        headlinePanel.setBackground(Color.white);
        LoginInfoFrameSettings loginInfoFrameSettings = new LoginInfoFrameSettings(headlineLabel,headlinePanel, "Twoje treningi");

        // Tworzenie panelu przewijania
        scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(60,90,415,190);
        scrollPane.setVisible(true);
    }
    private static class MyTableModel extends javax.swing.table.DefaultTableModel {
        public MyTableModel(Object[][] tableData, Object[] columnNames) {
            super(tableData, columnNames);
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return true;
        }
    }
}