package example.app.login;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import example.app.AthletesRepository;
import example.app.dbSettings.User;
import example.app.dbSettings.Athlete;
import org.hibernate.SessionFactory;

import static example.app.Start.getBackgroundImagePanel;
import static example.app.Start.getButtonPanel;
import static example.app.Start.getjLayeredPane;

// filtrowanie tu dodam
// po kliknieciu wyswietlam treningi zawodnika i jego email

public class ShowAthletes extends JFrame{
    public ShowAthletes(SessionFactory sessionFactory, JFrame to, User currentUser){
        AthletesRepository repository = new AthletesRepository(sessionFactory);
        List<Athlete> athletesList = repository.getAllAthletes();

        // dane do tabeli przchowuja dane o zawodnikach
        Object[][] tableData = new Object[athletesList.size()][];
        for (int i = 0; i < athletesList.size(); i++) {
            Athlete athlete = athletesList.get(i);
            Object[] rowData = {
                    athlete.getName(),
                    athlete.getSurname(),
                    athlete.getBirthyear(),
            };
            tableData[i] = rowData;
        }

        // nazwy kolumn
        String[] columnNames = {"Name", "Surname", "Birth year"};

        // tworze z tego tabele
        JTable table = new JTable(tableData, columnNames);

        // ustawienia tabeli
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 14));

        // Tworzenie panelu przewijania
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(60,90,415,190);
        scrollPane.setVisible(true);

        // ustawiam headline
        JLabel headlineLabel = new JLabel();
        JPanel headlinePanel = new JPanel();
        headlinePanel.setBackground(Color.white);
        LoginInfoFrameSettings loginInfoFrameSettings = new LoginInfoFrameSettings(headlineLabel,headlinePanel, "Wszyscy zawodnicy");

        // stare panele uniewidoczniam
        getBackgroundImagePanel().setVisible(false);
        getButtonPanel().setVisible(false);
        getjLayeredPane().setVisible(false);

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
                scrollPane.setVisible(false);
                buttonBack.setVisible(false);
                headlinePanel.setVisible(false);
            }
        });

        //obecne elementy dodaje do frema
        to.add(scrollPane);
        to.add(buttonBack);
        to.add(headlinePanel);
    }

}
