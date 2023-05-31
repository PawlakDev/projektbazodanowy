package example;

import example.buttons.Button1Settings;
import org.hibernate.SessionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowTrainings extends JFrame{
    private SessionFactory sessionFactory;

    JPanel buttonPanel;

    JButton[] button = new JButton[3];

    JFrame to;

    public ShowTrainings(SessionFactory sessionFactory, JFrame to, JPanel oldButtonPanel) throws HeadlessException {

        this.sessionFactory = sessionFactory;

        // blokuje stare przyciski
        oldButtonPanel.setVisible(false);
        oldButtonPanel.setEnabled(false);

        buttonPanel = new JPanel();
        buttonPanel.setBounds(0, 0, 180, 400);
        buttonPanel.setLayout(null);
        buttonPanel.setBackground(Color.white);
        to.add(buttonPanel);

        button[0] = new JButton();

        Button1Settings button0Settings = new Button1Settings(button[0], buttonPanel, 20, 20, 130, 100, true, "Szczegóły treningu");
        button[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // tutaj akcja po kliknięciu "szczegółowy widok"
                System.out.println("szczegółowy widok");

            }
        });


        button[1] = new JButton();
        Button1Settings button1Settings = new Button1Settings(button[1], buttonPanel, 20, 130, 130, 100, true, "Wyswietl treningi");

        button[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // tutaj akcja po kliknięciu "ogólny"
                ShowTrainingsAll TrainingsAll = new ShowTrainingsAll(sessionFactory, to, buttonPanel);

            }

        });

        button[2] = new JButton();
        Button1Settings button2Settings = new Button1Settings(button[2], buttonPanel, 20, 240, 130, 100, true, "Cofnij");
        button[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonPanel.setEnabled(false);
                buttonPanel.setVisible(false);

                oldButtonPanel.setVisible(true);
                oldButtonPanel.setEnabled(true);
            }
        });

}
}
