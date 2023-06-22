package example.app.login.stats;

import example.app.dbSettings.User;
import example.app.dbSettings.Workouts;
import org.hibernate.SessionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static example.app.Start.*;
import static example.app.Start.getBackgroundImagePanel;
import static example.app.login.ShowStats.*;

public class Bests extends JFrame {
    public Bests(SessionFactory sessionFactory, JFrame to, User currentUser, List<Workouts> workouts) {

        // Wyświetlanie statystyk
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new GridLayout(3, 1));
        statsPanel.setBounds(80, 100, 300, 150);
        statsPanel.setBackground(Color.white);

        // Trening z największą liczbą kilometrów
        Workouts longestDistanceWorkout = getLongestDistanceWorkout(workouts);
        JLabel longestDistanceLabel = new JLabel("Najdłuższy trening: " + longestDistanceWorkout.getKilometers() + " km");
        statsPanel.add(longestDistanceLabel);

        // Najdłuższy trening (czas)
        Workouts longestDurationWorkout = getLongestDurationWorkout(workouts);
        JLabel longestDurationLabel = new JLabel("Najdłuższy trening: " + longestDurationWorkout.getTimeworkout() + " minut");
        statsPanel.add(longestDurationLabel);

        // Najczęściej wykonywany typ treningu
        String mostFrequentType = getMostFrequentWorkoutType(workouts);
        JLabel mostFrequentTypeLabel = new JLabel("Najczęstszy typ treningu: " + mostFrequentType);
        statsPanel.add(mostFrequentTypeLabel);

        // button cofania
        JButton buttonBack = new JButton();
        buttonBack.setBackground(new Color(200, 230, 255));
        buttonBack.setVisible(true);
        buttonBack.setLayout(null);
        buttonBack.setBounds(70, 300, 100, 50);
        buttonBack.setText("Wstecz");
        buttonBack.setFocusable(false);
        buttonBack.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 150, 200), 2),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        buttonBack.setFont(new Font("Arial", Font.BOLD, 14));

        buttonBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getButtonBack().setVisible(true);
                buttonBack.setVisible(false);
                getButtonWykres1().setVisible(true);
                getButtonWykres2().setVisible(true);
                getButtonBest().setVisible(true);
                statsPanel.setVisible(false);
            }
        });

        to.add(statsPanel);
        to.add(buttonBack);
    }

    private Workouts getLongestDistanceWorkout(List<Workouts> workouts) {
        return Collections.max(workouts, (w1, w2) -> Double.compare(w1.getKilometers(), w2.getKilometers()));
    }

    private Workouts getLongestDurationWorkout(List<Workouts> workouts) {
        return Collections.max(workouts, (w1, w2) -> Integer.compare(w1.getTimeworkout(), w2.getTimeworkout()));
    }

    private String getMostFrequentWorkoutType(List<Workouts> workouts) {
        Map<String, Integer> workoutTypeCount = new HashMap<>();

        for (Workouts workout : workouts) {
            String workoutType = workout.getWorkouttype();
            workoutTypeCount.put(workoutType, workoutTypeCount.getOrDefault(workoutType, 0) + 1);
        }

        int maxCount = 0;
        String mostFrequentType = "";

        for (Map.Entry<String, Integer> entry : workoutTypeCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostFrequentType = entry.getKey();
            }
        }

        return mostFrequentType;
    }
}
