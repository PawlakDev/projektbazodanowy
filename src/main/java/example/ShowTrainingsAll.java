package example;

import example.InfoFrames.LoginInfoFrameSettings;
import org.hibernate.SessionFactory;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static example.Start.getBackgroundImagePanel;
//przerobić na okno z treningiem po kliknięciu na trening :)
public class ShowTrainingsAll extends JFrame {

    JLabel headlineLabel;
    JPanel headlinePanel;
    JTable table;
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



        JComboBox sortType1 = new JComboBox<>();
        sortType1.addItem("nazwa");
        sortType1.addItem("data");
        sortType1.setSelectedItem("data");

        JLabel sortTypeLabel1 = new JLabel("Sortuj po:");
        sortTypeLabel1.setFont(new Font("Arial", Font.BOLD, 10));

        //Panel do sortowania
        JPanel sortTypePanel1 = new JPanel();
        sortTypePanel1.setOpaque(false);
        sortTypePanel1.setBounds(230, 300, 70, 40);
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
        sortTypePanel2.setBounds(310, 300, 70, 40);
        sortTypePanel2.setVisible(true);
        sortTypePanel2.setLayout(new BorderLayout());
        sortTypePanel2.add(sortTypeLabel2, BorderLayout.NORTH);
        sortTypePanel2.add(sortType2, BorderLayout.CENTER);


        //button do sortowania
        JButton buttonSort = new JButton();
        buttonSort.setBackground(new Color(200, 230, 255));
        buttonSort.setVisible(true);
        buttonSort.setLayout(null);
        buttonSort.setBounds(393, 310, 40, 30);
        buttonSort.setText("Sortuj");
        buttonSort.setFocusable(false);
        buttonSort.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 150, 200), 2),
                BorderFactory.createEmptyBorder(1, 1, 1, 1)
        ));
        buttonSort.setFont(new Font("Arial", Font.BOLD, 8));

        // sortowanie wedlug tego co wybiore
        buttonSort.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String sortType = sortType1.getSelectedItem().toString();
                boolean ascending = sortType2.getSelectedItem().toString().equals("rosnąco");

                if (sortType.equals("nazwa")) {
                    Arrays.sort(tableData, Comparator.comparing(row -> row[1].toString()));
                } else if (sortType.equals("data")) {
                    Arrays.sort(tableData, Comparator.comparing(row -> row[0].toString()));
                }

                if (!ascending) {
                    // jesli malejace
                    for (int i = 0; i < tableData.length / 2; i++) {
                        Object[] temp = tableData[i];
                        tableData[i] = tableData[tableData.length - 1 - i];
                        tableData[tableData.length - 1 - i] = temp;
                    }
                }

                // odwiezam
                table.setModel(new MyTableModel(tableData, columnNames));
            }
        });

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
                buttonSort.setVisible(false);
                sortTypePanel2.setVisible(false);
                sortTypePanel1.setVisible(true);
            }
        });

        to.add(buttonSort);
        to.add(sortTypePanel1);
        to.add(sortTypePanel2);
        to.add(headlinePanel);
        to.add(buttonBack);
        to.add(scrollPane);

    }
    private static class MyTableModel extends javax.swing.table.DefaultTableModel {
        public MyTableModel(Object[][] tableData, Object[] columnNames) {
            super(tableData, columnNames);
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }
}
