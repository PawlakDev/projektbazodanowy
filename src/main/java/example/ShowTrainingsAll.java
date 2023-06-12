package example;

import example.InfoFrames.LoginInfoFrameSettings;
import org.hibernate.SessionFactory;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.List;

import static example.Start.getBackgroundImagePanel;

public class ShowTrainingsAll extends JFrame {

    JLabel headlineLabel;
    JPanel headlinePanel;
    JTable table;
    JTextArea textArea;
    JScrollPane scrollPane;

    JButton buttonBack;
    public ShowTrainingsAll(SessionFactory sessionFactory, JFrame to, JPanel oldButtonPanel, User currentUser) {
        System.out.println("show all trainings view");

        WorkoutRepository repository = new WorkoutRepository(sessionFactory);
        List<Workouts> userWorkouts = repository.getWorkoutsByUserId(currentUser.getId());
        
        // dane do tabeli przchowuja dane o treningacj
        Object[][] tableData = new Object[userWorkouts.size()][];
        for (int i = 0; i < userWorkouts.size(); i++) {
            Workouts workout = userWorkouts.get(i);
            Object[] rowData = {
                    workout.getDate(),
                    workout.getWorkouttype(),
                    workout.getKilometers(),
                    workout.getTimeworkout()
            };
            tableData[i] = rowData;
        }

        // nazwy kolumn
        String[] columnNames = {"Date", "Workout Type", "Kilometers", "Time (mins)"};

        // tworze z tego tabele
        table = new JTable(tableData, columnNames);

        // ustawienia tabeli
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 14)); 
        
        // wylaczam stare widoki
        oldButtonPanel.setVisible(false);
        oldButtonPanel.setEnabled(false);
        getBackgroundImagePanel().setVisible(false);

        // ustawiam headline
        headlineLabel = new JLabel();
        headlinePanel = new JPanel();
        headlinePanel.setBackground(Color.white);
        LoginInfoFrameSettings loginInfoFrameSettings = new LoginInfoFrameSettings(headlineLabel,headlinePanel, "Twoje treningi");

        // Tworzenie panelu przewijania
        scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(60,90,415,190);
        scrollPane.setVisible(true);

        buttonBack = new JButton();
        // next - Cofnij
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

                oldButtonPanel.setVisible(true);
                oldButtonPanel.setEnabled(true);
                getBackgroundImagePanel().setVisible(true);
                scrollPane.setVisible(false);
                buttonBack.setVisible(false);
                headlinePanel.setVisible(false);
            }
        });

        JComboBox sortType1 = new JComboBox<>();
        sortType1.addItem("nazwa");
        sortType1.addItem("data");
        sortType1.setSelectedItem("data");

        JLabel sortTypeLabel1 = new JLabel("Sortuj po:");
        sortTypeLabel1.setFont(new Font("Arial", Font.BOLD, 10));

        //Panel do sortowania
        JPanel sortTypePanel1 = new JPanel();
        sortTypePanel1.setOpaque(false);
        sortTypePanel1.setBounds(250, 300, 70, 40);
        sortTypePanel1.setVisible(true);
        sortTypePanel1.setLayout(new BorderLayout());
        sortTypePanel1.add(sortTypeLabel1, BorderLayout.NORTH);
        sortTypePanel1.add(sortType1, BorderLayout.CENTER);

        JComboBox sortType2 = new JComboBox<>();
        sortType2.addItem("malejąco");
        sortType2.addItem("rosnąco");
        sortType2.setSelectedItem("malejąco");

        JLabel sortTypeLabel2 = new JLabel("Sortuj:");
        sortTypeLabel2.setFont(new Font("Arial", Font.BOLD, 10));

        //Panel do roku
        JPanel sortTypePanel2 = new JPanel();
        sortTypePanel2.setOpaque(false);
        sortTypePanel2.setBounds(330, 300, 70, 40);
        sortTypePanel2.setVisible(true);
        sortTypePanel2.setLayout(new BorderLayout());
        sortTypePanel2.add(sortTypeLabel2, BorderLayout.NORTH);
        sortTypePanel2.add(sortType2, BorderLayout.CENTER);

        to.add(sortTypePanel1);
        to.add(sortTypePanel2);
        to.add(headlinePanel);
        to.add(buttonBack);
        to.add(scrollPane);

    }
}
