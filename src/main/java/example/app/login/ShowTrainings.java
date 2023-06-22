package example.app.login;//package example;

import example.app.WorkoutRepository;
import example.app.dbSettings.User;
import example.app.dbSettings.Workouts;
import example.app.login.stats.EditTraining;
import org.hibernate.SessionFactory;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static example.app.Start.getBackgroundImagePanel;

public class ShowTrainings extends JFrame {
    JLabel headlineLabel;
    JPanel headlinePanel;
    JTable table;
    JScrollPane scrollPane;
    JButton buttonBack;
    public ShowTrainings(SessionFactory sessionFactory, JFrame to, JPanel oldButtonPanel, User currentUser) {

        WorkoutRepository repository = new WorkoutRepository(sessionFactory);
        List<Workouts> userWorkouts = repository.getWorkoutsByUserId(currentUser.getId());

        // dane do tabeli przchowuja dane o treningach
        final Object[][][] tableData = {new Object[userWorkouts.size()][]};
        for (int i = 0; i < userWorkouts.size(); i++) {
            Workouts workout = userWorkouts.get(i);
            Object[] rowData = {
                    workout.getId(),
                    workout.getDate(),
                    workout.getWorkouttype(),
                    workout.getKilometers(),
                    workout.getTimeworkout()
            };
            tableData[0][i] = rowData;
        }

        // nazwy kolumn
        String[] columnNames = {"ID", "Date", "Workout Type", "Kilometers", "Time (mins)"};

        // tworze z tego tabele
        table = new JTable(tableData[0], columnNames);

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

        sortType1.addItem("Kilometry");
        sortType1.addItem("Time");
        sortType1.addItem("Date");
        sortType1.setSelectedItem("Date");

        JLabel sortTypeLabel1 = new JLabel("Sortuj po:");
        sortType1.setBounds(160, 250, 100, 30);
        sortType1.setFont(new Font("Arial", Font.PLAIN, 12));

        //Panel do sortowania
        JPanel sortTypePanel1 = new JPanel();
        sortTypePanel1.setVisible(true);
        sortTypePanel1.setEnabled(true);

        sortTypePanel1.setOpaque(false);
        sortTypePanel1.setBounds(160, 300, 70, 40);
        sortTypePanel1.setVisible(true);
        sortTypePanel1.setLayout(new BorderLayout());
        sortTypePanel1.add(sortTypeLabel1, BorderLayout.NORTH);
        sortTypePanel1.add(sortType1, BorderLayout.CENTER);

        JComboBox sortType2 = new JComboBox<>();
        sortType2.addItem("malejąco");
        sortType2.addItem("rosnąco");
        sortType2.setSelectedItem("malejąco");

        JLabel sortTypeLabel2 = new JLabel("Sortuj:");
        sortType2.setBounds(300, 250, 100, 30);
        sortType2.setFont(new Font("Arial", Font.PLAIN, 12));

        //Panel do roku
        JPanel sortTypePanel2 = new JPanel();
        sortTypePanel2.setOpaque(false);
        sortTypePanel2.setBounds(260, 300, 70, 40);
        sortTypePanel2.setVisible(true);
        sortTypePanel2.setLayout(new BorderLayout());
        sortTypePanel2.add(sortTypeLabel2, BorderLayout.NORTH);
        sortTypePanel2.add(sortType2, BorderLayout.CENTER);

        //button do sortowania
        JButton buttonSort = new JButton();
        buttonSort.setBackground(new Color(200, 230, 255));
        buttonSort.setVisible(true);
        buttonSort.setLayout(null);
        buttonSort.setBounds(350, 300, 100, 40);
        buttonSort.setFont(new Font("Arial", Font.BOLD, 12));
        buttonSort.setText("Sortuj");
        buttonSort.setFocusable(false);
        buttonSort.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 150, 200), 2),
                BorderFactory.createEmptyBorder(1, 1, 1, 1)
        ));
        buttonSort.setFont(new Font("Arial", Font.BOLD, 10));

        //button do wybrania treningu
        JButton buttonSelect = new JButton();
        buttonSelect.setBackground(new Color(200, 230, 255));
        buttonSelect.setVisible(true);
        buttonSelect.setLayout(null);
        buttonSelect.setBounds(450, 280, 80, 40);
        buttonSelect.setFont(new Font("Arial", Font.BOLD, 12));
        buttonSelect.setText("Wybierz");
        buttonSelect.setFocusable(false);
        buttonSelect.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 150, 200), 2),
                BorderFactory.createEmptyBorder(1, 1, 1, 1)
        ));
        JButton buttonDelete = new JButton();
        buttonDelete.setBackground(new Color(200, 230, 255));
        buttonDelete.setVisible(true);
        buttonDelete.setLayout(null);
        buttonDelete.setBounds(450, 320, 80, 40);
        buttonDelete.setFont(new Font("Arial", Font.BOLD, 12));
        buttonDelete.setText("Usuń");
        buttonDelete.setFocusable(false);
        buttonDelete.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 150, 200), 2),
                BorderFactory.createEmptyBorder(1, 1, 1, 1)
        ));

        // sortowanie wedlug tego co wybiore
        buttonSort.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String sortType = sortType1.getSelectedItem().toString();
                boolean ascending = sortType2.getSelectedItem().toString().equals("rosnąco");

                if (sortType.equals("Kilometry")) {
                    Arrays.sort(tableData[0], Comparator.comparing(row -> Double.parseDouble(row[3].toString())));
                } else if (sortType.equals("Time")) {
                    Arrays.sort(tableData[0], Comparator.comparing(row -> Integer.parseInt(row[4].toString())));
                } else if (sortType.equals("Date")) {
                    Arrays.sort(tableData[0], Comparator.comparing(row -> row[1].toString()));
                }

                if (!ascending) {
                    // jeśli malejące
                    Object[][] reversedTableData = new Object[tableData[0].length][];
                    for (int i = 0; i < tableData[0].length; i++) {
                        reversedTableData[i] = tableData[0][tableData[0].length - 1 - i];
                    }
                    tableData[0] = reversedTableData;
                }

                // odświeżam
                table.setModel(new MyTableModel(tableData[0], columnNames));
            }
        });

        buttonSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    Object selectedId = table.getValueAt(selectedRow, 0);
                    int SelectedId = Integer.parseInt(selectedId.toString());
                    // Tutaj możesz wykorzystać odczytane ID wpisu
                    System.out.println("Wybrano ID wpisu: " + selectedId);
                    EditTraining editTraining = new EditTraining(sessionFactory, to, currentUser, userWorkouts, SelectedId);
                    buttonSelect.setVisible(false);
                    buttonDelete.setVisible(false);
                    buttonSort.setVisible(false);
                    sortTypePanel1.setVisible(false);
                    sortTypePanel2.setVisible(false);
                }
            }
        });
        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    Object selectedId = table.getValueAt(selectedRow, 0);
                    int confirmDialog = JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz usunąć ten wpis?", "Potwierdź usunięcie", JOptionPane.YES_NO_OPTION);
                    if (confirmDialog == JOptionPane.YES_OPTION) {
                        // Usunięcie wpisu z bazy danych
                        WorkoutRepository repository = new WorkoutRepository(sessionFactory);
                        repository.deleteWorkoutById((Long) selectedId);

                        // Odświeżenie tabeli
                        userWorkouts.remove(selectedRow);
                        JOptionPane.showMessageDialog(null, "Wpis został pomyślnie usunięty.", "Usunięcie wpisu", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });

        buttonBack = new JButton();
        // next - Cofnij
        buttonBack.setBackground(new Color(200, 230, 255));
        buttonBack.setVisible(true);
        buttonBack.setLayout(null);
        buttonBack.setBounds(50, 300, 100, 40);
        buttonBack.setFont(new Font("Arial", Font.BOLD, 12));
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

                sortTypePanel1.setVisible(false);
                sortTypePanel1.setEnabled(false);

                buttonSelect.setVisible(false);
                buttonSelect.setEnabled(false);

                buttonDelete.setVisible(false);
                buttonDelete.setEnabled(false);
            }
        });
        to.add(buttonSelect);
        to.add(buttonSort);
        to.add(sortTypePanel1);
        to.add(sortTypePanel2);
        to.add(headlinePanel);
        to.add(buttonBack);
        to.add(scrollPane);
        to.add(buttonDelete);

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
