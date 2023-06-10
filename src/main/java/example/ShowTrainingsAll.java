package example;

import example.InfoFrames.LoginInfoFrameSettings;
import org.hibernate.SessionFactory;

import javax.swing.*;

import java.awt.*;
import java.util.List;

import static example.Start.getBackgroundImagePanel;

public class ShowTrainingsAll extends JFrame {

    JLabel headlineLabel;
    JPanel headlinePanel;
    JLayeredPane LayeredPane;

    JTextArea textArea;
    JScrollPane scrollPane;
    public ShowTrainingsAll(SessionFactory sessionFactory, JFrame to, JPanel oldButtonPanel, User currentUser) {
        System.out.println("show all trainings view");

        WorkoutRepository repository = new WorkoutRepository(sessionFactory);
        List<Workouts> userWorkouts = repository.getWorkoutsByUserId(currentUser.getId());

        String treningi = "";
        // zapisywanie workouts uzytkownika do jednego stringa (z tego zrobie liste pozniej)
        for (Workouts workout : userWorkouts) {
            treningi += (workout.toString());
        }

        // wylaczam stare widoki
        oldButtonPanel.setVisible(false);
        oldButtonPanel.setEnabled(false);
        getBackgroundImagePanel().setVisible(false);

        // ustawiam headline
        headlineLabel = new JLabel();
        headlinePanel = new JPanel();
        headlinePanel.setBackground(Color.white);
        LoginInfoFrameSettings loginInfoFrameSettings = new LoginInfoFrameSettings(headlineLabel,headlinePanel, "Twoje treningi");

        to.add(headlinePanel);

        // ustawiam pole tekstoowe
        textArea = new JTextArea();
        textArea.setText(treningi);

        // Tworzenie panelu przewijania
        scrollPane = new JScrollPane(textArea);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(400, 300)); // Możesz dostosować szerokość i wysokość według potrzeb
        scrollPane.setBounds(70,100,400,230);
        textArea.setEditable(false); // Uniemożliwienie edycji JTextArea
        scrollPane.setVisible(true);

        to.add(scrollPane);

    }
}
