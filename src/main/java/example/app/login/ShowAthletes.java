package example.app.login;
import javax.swing.*;
import java.awt.*;
import java.util.List;

import example.app.AthletesRepository;
import example.app.dbSettings.User;
import example.app.dbSettings.Athlete;
import org.hibernate.SessionFactory;

public class ShowAthletes extends JFrame{
    public ShowAthletes(SessionFactory sessionFactory, JFrame to, User currentUser){
        AthletesRepository repository = new AthletesRepository(sessionFactory);
        List<Athlete> athletesList = repository.getAthletesByUserId(currentUser.getId());

        // dane do tabeli przchowuja dane o treningacj
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

        to.add(scrollPane);
    }

}
