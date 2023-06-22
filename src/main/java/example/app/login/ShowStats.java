package example.app.login;

import example.app.buttons.Button2Settings;
import example.app.dbSettings.User;
import example.app.dbSettings.Workouts;
import example.app.login.stats.KilometerChart;
import example.app.login.stats.MinutesChart;
import example.app.login.stats.Bests;
import org.hibernate.SessionFactory;
import example.app.WorkoutRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static example.app.Start.*;

public class ShowStats extends JFrame{

    private static JButton buttonBest;
    private static JButton buttonWykres1;
    private static JButton buttonWykres2;

    public static JButton getButtonBack() {
        return buttonBack;
    }

    private static JButton buttonBack;
    public ShowStats(SessionFactory sessionFactory, JFrame to, User currentUser)
    {
        // stare panele uniewidoczniam
        getBackgroundImagePanel().setVisible(false);
        getButtonPanel().setVisible(false);
        getjLayeredPane().setVisible(false);

        // ustawiam headline
        JLabel headlineLabel = new JLabel();
        JPanel headlinePanel = new JPanel();
        headlinePanel.setBackground(Color.white);
        LoginInfoFrameSettings loginInfoFrameSettings = new LoginInfoFrameSettings(headlineLabel,headlinePanel, "Twoje statystyki");

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10)); // Using GridLayout with 3 rows, 1 column, and spacing between components
        buttonWykres1 = new JButton();
        buttonWykres2 = new JButton();
        buttonBest = new JButton();


        Button2Settings button1Settings = new Button2Settings(buttonWykres1, buttonPanel, 40, 130, 130, 100, "Kilometry");
        Button2Settings button2Settings = new Button2Settings(buttonWykres2, buttonPanel, 180, 130, 130, 100, "Czas");
        Button2Settings button3Settings = new Button2Settings(buttonBest, buttonPanel, 330, 130, 130, 100, "Najlepsze");

        buttonWykres1.setVisible(true);
        buttonWykres2.setVisible(true);
        buttonBest.setVisible(true);

        // button cofania
        buttonBack = new JButton();
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

        WorkoutRepository repository = new WorkoutRepository(sessionFactory);
        List<Workouts> workouts = repository.getWorkoutsByUserId(currentUser.getId());

        buttonWykres1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonWykres1.setVisible(false);
                buttonWykres2.setVisible(false);
                buttonBest.setVisible(false);
                buttonBack.setVisible(false);
                KilometerChart kilometerChart = new KilometerChart(sessionFactory, to, currentUser, workouts);
            }
        });

        buttonWykres2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonWykres1.setVisible(false);
                buttonWykres2.setVisible(false);
                buttonBest.setVisible(false);
                buttonBack.setVisible(false);
                MinutesChart timeChart = new MinutesChart(sessionFactory, to, currentUser, workouts);
            }
        });

        buttonBest.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {buttonWykres1.setVisible(false);
                buttonWykres1.setVisible(false);
                buttonWykres2.setVisible(false);
                buttonBest.setVisible(false);
                buttonBack.setVisible(false);
                Bests bests = new Bests(sessionFactory, to, currentUser, workouts);
            }
        });

        buttonBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                buttonBack.setVisible(false);
                buttonWykres1.setVisible(false);
                buttonWykres2.setVisible(false);
                buttonBest.setVisible(false);
                getBackgroundImagePanel().setVisible(true);
                getButtonPanel().setVisible(true);
                getjLayeredPane().setVisible(true);
                getBackgroundImagePanel().setVisible(true);
                headlinePanel.setVisible(false);
            }
        });

        //obecne elementy dodaje do frema
        to.add(buttonBack);
        to.add(buttonWykres1);
        to.add(buttonWykres2);
        to.add(buttonBest);
        to.add(buttonBack);
        to.add(buttonPanel);
        to.add(headlinePanel);
    }
    public static JButton getButtonWykres1() {
        return buttonWykres1;
    }

    public static JButton getButtonBest() {
        return buttonBest;
    }
    public static JButton getButtonWykres2() {
        return buttonWykres2;
    }
}
