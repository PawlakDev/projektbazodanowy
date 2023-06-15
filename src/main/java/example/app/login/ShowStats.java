package example.app.login;

import example.app.buttons.Button2Settings;
import example.app.dbSettings.User;
import example.app.dbSettings.Workouts;
import example.app.login.stats.KilometerChart;
import example.app.login.stats.MinutesChart;
import example.app.login.stats.Bests;
import org.hibernate.SessionFactory;
import example.app.WorkoutRepository;

import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static example.app.Start.*;

public class ShowStats extends JFrame{

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
        JButton buttonWykres1 = new JButton();
        JButton buttonWykres2 = new JButton();
        JButton buttonBest = new JButton();

        Button2Settings button1Settings = new Button2Settings(buttonWykres1, buttonPanel, 40, 130, 130, 100, "Kilometry");
        Button2Settings button2Settings = new Button2Settings(buttonWykres2, buttonPanel, 180, 130, 130, 100, "Czas");
        Button2Settings button3Settings = new Button2Settings(buttonBest, buttonPanel, 330, 130, 130, 100, "Najlepsze");

        buttonWykres1.setVisible(false);
        buttonWykres2.setVisible(false);
        buttonBest.setVisible(false);

//        @Override
//        public void actionPerformed(ActionEvent e) {
//
//        if (e.getSource() == ) {
//        }
//        }

        WorkoutRepository repository = new WorkoutRepository(sessionFactory);
        List<Workouts> workouts = repository.getWorkoutsByUserId(currentUser.getId());

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
                buttonWykres1.setVisible(false);
                buttonWykres2.setVisible(false);
                buttonBest.setVisible(false);
                getBackgroundImagePanel().setVisible(true);
                getButtonPanel().setVisible(true);
                getjLayeredPane().setVisible(true);
                getBackgroundImagePanel().setVisible(true);
            }
        });

        //obecne elementy dodaje do frema
        to.add(buttonBack);
        to.add(headlinePanel);
        to.add(buttonWykres1);
        to.add(buttonWykres2);
        to.add(buttonBest);

    }
}
