package example.app.login.stats;
import example.app.dbSettings.User;
import example.app.dbSettings.Workouts;
import example.app.login.ShowStats;

import org.hibernate.SessionFactory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static example.app.Start.*;
import static example.app.Start.getBackgroundImagePanel;
import static example.app.login.ShowStats.*;

public class MinutesChart extends JFrame {
    public MinutesChart(SessionFactory sessionFactory, JFrame to, User currentUser, List<Workouts> workouts)
    {
        // pobieram moje dane z tabel czas
        DefaultCategoryDataset datasetTime = new DefaultCategoryDataset();
        for (Workouts workout : workouts) {
            datasetTime.addValue(workout.getTimeworkout(), "Training", workout.getDate());
        }

        // Konwersja na CategoryDataset
        CategoryDataset categoryDataset2 = datasetTime;

        // Tworzenie wykresu
        JFreeChart chartTime = ChartFactory.createBarChart(
                "Ilość minut w dany dzień",     // Tytuł wykresu
                "Dzień",                     // Etykieta osi X
                "Ilosc minut",     // Etykieta osi Y
                datasetTime,           // Zestaw danych
                PlotOrientation.VERTICAL,  // Orientacja wykresu
                false,                      // Wyświetlanie legendy
                false,                      // Wyświetlanie wskazówek
                false                      // Wyłączenie URL-ów
        );

        // Tworzenie panelu wykresu i dodanie do niego wykresu
        ChartPanel chartTimePanel = new ChartPanel(chartTime);
        chartTimePanel.setVisible(true);
        chartTimePanel.setBounds(60, 120, 320, 200);
        chartTimePanel.setOpaque(false);

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
                chartTimePanel.setVisible(false);
            }
        });
        
        to.add(chartTimePanel);
        to.add(buttonBack);

    }
}
