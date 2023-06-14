package example.app.login;

import example.app.dbSettings.User;
import example.app.dbSettings.Workouts;
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

        WorkoutRepository repository = new WorkoutRepository(sessionFactory);
        List<Workouts> workouts = repository.getWorkoutsByUserId(currentUser.getId());

        // kalendarz to musze do innej klasy przeniesc
        Calendar calendar = Calendar.getInstance();

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
        chartKilometersPanel.setBounds(60,90,270,100);
        chartKilometersPanel.setOpaque(false);

        // pobieram moje dane z tabel czas
        DefaultCategoryDataset datasetTime = new DefaultCategoryDataset();
        for (Workouts workout : workouts) {
            datasetTime.addValue(workout.getTimeworkout(), "Training", workout.getDate());
        }

        // Konwersja na CategoryDataset
        CategoryDataset categoryDataset2 = datasetKilometers;

        // Tworzenie wykresu
        JFreeChart chartTime = ChartFactory.createBarChart(
                "Ilość minut w dany dzień",     // Tytuł wykresu
                "Dzień",                     // Etykieta osi X
                "Ilosc minut",     // Etykieta osi Y
                categoryDataset,           // Zestaw danych
                PlotOrientation.VERTICAL,  // Orientacja wykresu
                false,                      // Wyświetlanie legendy
                false,                      // Wyświetlanie wskazówek
                false                      // Wyłączenie URL-ów
        );

        // Tworzenie panelu wykresu i dodanie do niego wykresu
        ChartPanel chartTimePanel = new ChartPanel(chartKilometers);
        chartTimePanel.setVisible(true);
        chartTimePanel.setBounds(60,205,270,100);
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
                getBackgroundImagePanel().setVisible(true);
                getButtonPanel().setVisible(true);
                getjLayeredPane().setVisible(true);
                getBackgroundImagePanel().setVisible(true);
            }
        });

        //obecne elementy dodaje do frema
        to.add(buttonBack);
        to.add(headlinePanel);
        to.add(chartKilometersPanel);
        to.add(chartTimePanel);
    }
}
