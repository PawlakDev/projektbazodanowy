package example;

import example.InfoFrames.LoginInfoFrameSettings;
import org.hibernate.SessionFactory;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        scrollPane.setBounds(60,90,415,190);
        textArea.setEditable(false);
        scrollPane.setVisible(true);

        JButton ButtonBack = new JButton();
        // next - Cofnij
        ButtonBack.setBackground(new Color(200, 230, 255));
        ButtonBack.setVisible(true);
        ButtonBack.setLayout(null);
        ButtonBack.setBounds(70, 300, 100, 50);
        ButtonBack.setText("Wstecz");
        ButtonBack.setFocusable(false);
        ButtonBack.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 150, 200), 2),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        ButtonBack.setFont(new Font("Arial", Font.BOLD, 14));

        ButtonBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                oldButtonPanel.setVisible(true);
                oldButtonPanel.setEnabled(true);
                getBackgroundImagePanel().setVisible(true);
                scrollPane.setVisible(false);
                ButtonBack.setVisible(false);
                headlinePanel.setVisible(false);
            }
        });

        to.add(ButtonBack);
        to.add(scrollPane);

    }
}
