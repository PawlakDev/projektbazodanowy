package example.app.login;

import example.app.WorkoutRepository;
import example.app.dbSettings.User;
import example.app.dbSettings.Workouts;
import org.hibernate.SessionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static example.app.Start.ButtonPanel;
import static example.app.Start.jFrame;

public class EditTraining extends JFrame {
    private JLabel headlineLabel;
    private JPanel headlinePanel;
    private JTable table;
    private JScrollPane scrollPane;
    private JButton buttonBack;
    private JButton buttonSave;
    private SessionFactory sessionFactory;
    private User currentUser;
    private Workouts selectedWorkout;

    public EditTraining(SessionFactory sessionFactory, User currentUser, Workouts selectedWorkout) {
        this.sessionFactory = sessionFactory;
        this.currentUser = currentUser;
        this.selectedWorkout = selectedWorkout;

        headlineLabel = new JLabel();
        headlinePanel = new JPanel();
        headlinePanel.setBackground(Color.white);
        LoginInfoFrameSettings loginInfoFrameSettings = new LoginInfoFrameSettings(headlineLabel, headlinePanel, "Edytuj trening");

        // Create a panel to hold the input components for editing
        JPanel editPanel = new JPanel(new GridLayout(6, 2));
        editPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField(String.valueOf(selectedWorkout.getId()));
        idField.setEditable(false);
        editPanel.add(idLabel);
        editPanel.add(idField);

        JLabel dateLabel = new JLabel("Data:");
        JTextField dateField = new JTextField(selectedWorkout.getDate());
        editPanel.add(dateLabel);
        editPanel.add(dateField);

        JLabel workoutTypeLabel = new JLabel("Typ treningu:");
        JTextField workoutTypeField = new JTextField(selectedWorkout.getWorkouttype());
        editPanel.add(workoutTypeLabel);
        editPanel.add(workoutTypeField);

        JLabel kilometersLabel = new JLabel("Kilometry:");
        JTextField kilometersField = new JTextField(String.valueOf(selectedWorkout.getKilometers()));
        editPanel.add(kilometersLabel);
        editPanel.add(kilometersField);

        JLabel timeLabel = new JLabel("Czas (minuty):");
        JTextField timeField = new JTextField(String.valueOf(selectedWorkout.getTimeworkout()));
        editPanel.add(timeLabel);
        editPanel.add(timeField);

        JLabel opisLabel = new JLabel("Opis:");
        JTextField OpisField = new JTextField(String.valueOf(selectedWorkout.getDescription()));
        editPanel.add(opisLabel);
        editPanel.add(OpisField);


        buttonSave = new JButton("Zatwierdź");
        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update the selected workout with the new values
                selectedWorkout.setDate(dateField.getText());
                selectedWorkout.setWorkouttype(workoutTypeField.getText());
                selectedWorkout.setKilometers((int) Double.parseDouble(kilometersField.getText()));
                selectedWorkout.setTimeworkout(Integer.parseInt(timeField.getText()));
                selectedWorkout.setDescription(OpisField.getText());

                // Save the changes to the database
                WorkoutRepository repository = new WorkoutRepository(sessionFactory);
                repository.updateWorkout(selectedWorkout);

                // Show a message dialog to inform the user
                JOptionPane.showMessageDialog(null, "Zmiany zostały zapisane.", "Zapisano zmiany", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        buttonBack = new JButton("Cofnij");
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the EditTraining window
                Window parentWindow = SwingUtilities.getWindowAncestor(EditTraining.this);
                if (parentWindow instanceof ShowTrainings) {
                    ShowTrainings showTrainings = (ShowTrainings) parentWindow;
                    showTrainings.refreshTableData();
                }
                dispose();
            }
        });

        // Create a panel to hold the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(buttonSave);
        buttonPanel.add(buttonBack);

        // Create a panel to hold the edit panel and button panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(editPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Set up the JFrame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(400, 300));
        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

