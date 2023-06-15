package example.app.login.stats;
import example.app.dbSettings.User;
import example.app.dbSettings.Workouts;

import org.hibernate.SessionFactory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

public class KilometerChart extends JFrame {
    public KilometerChart(SessionFactory sessionFactory, JFrame to, User currentUser, List<Workouts> workouts)
    {
        // pobieram moje dane z tabel do kilometrów
        DefaultCategoryDataset datasetKilometers = new DefaultCategoryDataset();
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (int i = 0; i < 5; i++) {
            LocalDate weekStart = currentDate.minusWeeks(i).with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
            LocalDate weekEnd = currentDate.minusWeeks(i).with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

            int sumKilometers = workouts.stream()
                    .filter(workout -> {
                        LocalDate workoutDate = LocalDate.parse(workout.getDate(), formatter);
                        return !workoutDate.isBefore(weekStart) && !workoutDate.isAfter(weekEnd);
                    })
                    .mapToInt(Workouts::getKilometers)
                    .sum();

            datasetKilometers.addValue(sumKilometers, "Training", weekStart.format(formatter));
        }

        // Konwersja na CategoryDataset
        CategoryDataset categoryDataset = datasetKilometers;

        // Tworzenie wykresu
        JFreeChart chartKilometers = ChartFactory.createBarChart(
                "Kilometry w dany tydzień",     // Tytuł wykresu
                "tydzień",                     // Etykieta osi X
                "suma kilometrów",     // Etykieta osi Y
                categoryDataset,           // Zestaw danych
                PlotOrientation.VERTICAL,  // Orientacja wykresu
                false,                      // Wyświetlanie legendy
                false,                      // Wyświetlanie wskazówek
                false                      // Wyłączenie URL-ów
        );

        // Tworzenie panelu wykresu i dodanie do niego wykresu
        ChartPanel chartKilometersPanel = new ChartPanel(chartKilometers);
        chartKilometersPanel.setVisible(true);
        chartKilometersPanel.setBounds(60, 120, 320, 200);
        chartKilometersPanel.setOpaque(false);
        to.add(chartKilometersPanel);
    }
}
