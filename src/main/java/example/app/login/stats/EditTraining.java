package example.app.login.stats;
import example.app.dbSettings.User;
import example.app.dbSettings.Workouts;

import example.app.login.LoginInfoFrameSettings;
import example.app.login.ShowTrainings;
import org.hibernate.SessionFactory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import example.app.WorkoutRepository;
import example.app.dbSettings.User;
import example.app.dbSettings.Workouts;
import example.app.login.stats.EditTraining;
import org.hibernate.SessionFactory;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static example.app.Start.getBackgroundImagePanel;
import static example.app.login.ShowTrainings.*;

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