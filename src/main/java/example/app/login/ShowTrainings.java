package example.app.login;

import example.app.buttons.Button1Settings;
import example.app.dbSettings.User;
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

    public ShowTrainings(SessionFactory sessionFactory, JFrame to, JPanel oldButtonPanel, User currentUser) throws HeadlessException {

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

        Button1Settings button0Settings = new Button1Settings(button[0], buttonPanel, 20, 20, 130, 100, false, "Wyswietl zawodników");
        if(currentUser.getIscoach() == true)
            button[0].setEnabled(true);
        button[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // tutaj akcja po kliknięciu "wyswietl zawodników"
                // zastosować nową tabelke athletes do tego a usera juz zostawic
                System.out.println("wyświetl zawodników");

            }
        });


        button[1] = new JButton();
        Button1Settings button1Settings = new Button1Settings(button[1], buttonPanel, 20, 130, 130, 100, true, "Wyswietl treningi");

        button[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // tutaj akcja po kliknięciu "ogólny"
                ShowTrainingsAll TrainingsAll = new ShowTrainingsAll(sessionFactory, to, buttonPanel, currentUser);

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
