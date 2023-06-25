package example.app.login.stats;
import example.app.dbSettings.Workouts;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.Locale;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.List;

import static example.app.login.ShowStats.*;

public class KilometerChart extends JFrame {
    public KilometerChart(JFrame to, List<Workouts> workouts)
    {
        // pobieram moje dane z tabel do kilometrów
        DefaultCategoryDataset datasetKilometers = new DefaultCategoryDataset();
        LocalDate currentDate = LocalDate.now();
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
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

            int weekNumber = weekStart.get(weekFields.weekOfWeekBasedYear());
            datasetKilometers.addValue(sumKilometers, "Training", String.valueOf(weekNumber));
        }

        // Konwersja na CategoryDataset
        CategoryDataset categoryDataset = datasetKilometers;

        // Tworzenie wykresu
        JFreeChart chartKilometers = ChartFactory.createBarChart(
                "Kilometry w dany tydzień",     // Tytuł wykresu
                "Numer tygodnia w roku",                     // Etykieta osi X
                "Suma kilometrów",     // Etykieta osi Y
                categoryDataset,           // Zestaw danych
                PlotOrientation.VERTICAL,  // Orientacja wykresu
                false,                      // Wyświetlanie legendy
                false,                      // Wyświetlanie wskazówek
                false                      // Wyłączenie URL-ów
        );

        // Tworzenie panelu wykresu i dodanie do niego wykresu
        ChartPanel chartKilometersPanel = new ChartPanel(chartKilometers);
        chartKilometersPanel.setVisible(true);
        chartKilometersPanel.setBounds(80, 100, 330, 190);
        chartKilometersPanel.setOpaque(false);

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

                getButtonBack().setVisible(true);
                buttonBack.setVisible(false);
                getButtonWykres1().setVisible(true);
                getButtonWykres2().setVisible(true);
                getButtonBest().setVisible(true);
                chartKilometersPanel.setVisible(false);
            }
        });

        to.add(chartKilometersPanel);
        to.add(buttonBack);
    }
}
