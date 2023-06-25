package example.app.login.stats;

import example.app.dbSettings.Workouts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static example.app.login.ShowStats.*;

public class Bests extends JFrame {

    public Bests(JFrame to, List<Workouts> workouts) {

        // wyświetlanie statystyk
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new GridLayout(3, 1));
        statsPanel.setBounds(60, 120, 415, 190);
        statsPanel.setBackground(Color.white);
        statsPanel.setOpaque(false);

        // trening z największą liczbą kilometrów
        Workouts longestDistanceWorkout = getLongestDistanceWorkout(workouts);
        JLabel longestDistanceLabel = new JLabel("<html> Najdłuższy dystans na treningu: " + longestDistanceWorkout.getKilometers() + " km </html");
        longestDistanceLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 150, 200), 2),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        statsPanel.add(longestDistanceLabel);

        // najdłuższy trening
        Workouts longestDurationWorkout = getLongestDurationWorkout(workouts);
        JLabel longestDurationLabel = new JLabel("<html> Najdłuższy trening: " + longestDurationWorkout.getTimeworkout() + "</html");
        longestDurationLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 150, 200), 2),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        statsPanel.add(longestDurationLabel);

        // najczęszy typ treningu
        String mostFrequentType = getMostFrequentWorkoutType(workouts);
        String mostFrequentTypeText = "<html> Najczęstszy typ treningu: " + mostFrequentType + "</html";
        JLabel mostFrequentTypeLabel = new JLabel(mostFrequentTypeText);
        mostFrequentTypeLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 150, 200), 2),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        statsPanel.add(mostFrequentTypeLabel);

        // najszybszy bieg
        Workouts fastestRun = getFastestRun(workouts);
        double speed = fastestRun.getKilometers() / (fastestRun.getTimeworkout() / 60.0);
        speed = Math.round(speed * 100.0) / 100.0;
        String fastestRunText = "<html>Najszybszy bieg: " + fastestRun.getDate() + ":<br>" + speed + " km/h</html>";
        JLabel fastestRunLabel = new JLabel(fastestRunText);
        fastestRunLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 150, 200), 2),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        statsPanel.add(fastestRunLabel);

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
    private Workouts getFastestRun(List<Workouts> workouts) {
        Workouts fastestRun = null;
        double maxSpeed = Double.MIN_VALUE;

        for (Workouts workout : workouts) {
            double speed = workout.getKilometers() / (workout.getTimeworkout() / 60.0); // Obliczenie średniej prędkości (kilometry na godzinę)

            if (speed > maxSpeed) {
                maxSpeed = speed;
                fastestRun = workout;
            }
        }
        return fastestRun;
    }
}
