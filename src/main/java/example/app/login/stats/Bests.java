package example.app.login.stats;

import example.app.dbSettings.User;
import example.app.dbSettings.Workouts;
import org.hibernate.SessionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static example.app.Start.*;
import static example.app.Start.getBackgroundImagePanel;
import static example.app.login.ShowStats.*;

public class Bests extends JFrame {
    public Bests(SessionFactory sessionFactory, JFrame to, User currentUser, List<Workouts> workouts){

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
                buttonBack.setVisible(false);
                getButtonWykres1().setVisible(true);
                getButtonWykres2().setVisible(true);
                getButtonBest().setVisible(true);
            }
        });

        to.add(buttonBack);
    }

}
