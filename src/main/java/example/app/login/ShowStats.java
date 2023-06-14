package example.app.login;

import example.app.dbSettings.User;
import org.hibernate.SessionFactory;

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

        // Tworzenie przykladowego zestawu danych
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(5, "Training", "Monday");
        dataset.addValue(10, "Training", "Tuesday");
        dataset.addValue(8, "Training", "Wednesday");
        dataset.addValue(3, "Training", "Thursday");
        dataset.addValue(6, "Training", "Friday");

        // Konwersja na CategoryDataset
        CategoryDataset categoryDataset = dataset;

        // Tworzenie wykresu
        JFreeChart chart = ChartFactory.createBarChart(
                "Training Statistics Demo",     // Tytuł wykresu
                "Day",                     // Etykieta osi X
                "Number of Trainings",     // Etykieta osi Y
                categoryDataset,           // Zestaw danych
                PlotOrientation.VERTICAL,  // Orientacja wykresu
                true,                      // Wyświetlanie legendy
                true,                      // Wyświetlanie wskazówek
                false                      // Wyłączenie URL-ów
        );

        // Tworzenie panelu wykresu i dodanie do niego wykresu
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setVisible(true);
        chartPanel.setBounds(60,90,415,190);

        // Dodanie panelu wykresu do głównego panelu
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setVisible(true);

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

                getBackgroundImagePanel().setVisible(true);
                getButtonPanel().setVisible(true);
                getjLayeredPane().setVisible(true);
                getBackgroundImagePanel().setVisible(true);
            }
        });

        //obecne elementy dodaje do frema
        to.add(buttonBack);
        to.add(mainPanel);
        to.add(headlinePanel);
        to.add(chartPanel);
    }
}
