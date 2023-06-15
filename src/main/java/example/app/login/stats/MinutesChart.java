package example.app.login.stats;
import example.app.dbSettings.User;
import example.app.dbSettings.Workouts;

import example.app.dbSettings.Workouts;
import org.hibernate.SessionFactory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.List;

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
        
        to.add(chartTimePanel);

    }
}
