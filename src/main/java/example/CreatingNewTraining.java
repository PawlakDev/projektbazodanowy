package example;

import example.InfoFrames.WelcomeMsgSettings;
import example.buttons.Button1Settings;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;

public class CreatingNewTraining {
    JPanel ButtonPanel4, wellcomeMsgPanel;
    JLabel wellcomeMsgLabel;
    JButton[] button = new JButton[6]; // wartosc do zmiany
    public CreatingNewTraining(SessionFactory sessionFactory, JPanel ButtonPanel3, JPanel BackgroundImagePanel, JFrame to, JLayeredPane jLayeredPane) {
        ButtonPanel3.setEnabled(false);
        ButtonPanel3.setVisible(false);

        ButtonPanel4 = new JPanel();
        ButtonPanel4.setBounds(0, 0, 500, 400);
        ButtonPanel4.setLayout(null);
//        //Ustawienie koloru tla
        ButtonPanel4.setBackground(new Color(0, 0, 0, 0));

        wellcomeMsgPanel = new JPanel();
        wellcomeMsgLabel = new JLabel();

        WelcomeMsgSettings welcomeMsgSettings = new WelcomeMsgSettings(wellcomeMsgLabel, wellcomeMsgPanel, "Wybierz typ treningu", 70,380);

        jLayeredPane.add(wellcomeMsgPanel, JLayeredPane.PALETTE_LAYER); //
        jLayeredPane.add(ButtonPanel4, JLayeredPane.PALETTE_LAYER); //

        button[0] = new JButton();
        Button1Settings button1Settings = new Button1Settings(button[0], ButtonPanel4, 40, 120, 130, 100, true, "Bieg");

        button[1] = new JButton();
        Button1Settings button2Settings = new Button1Settings(button[1], ButtonPanel4, 210, 120, 130, 100, true, "Woda");

        button[2] = new JButton();
        Button1Settings button3Settings = new Button1Settings(button[2], ButtonPanel4, 370, 120, 130, 100, true, "Silownia");

        button[3] = new JButton();
        Button1Settings button4Settings = new Button1Settings(button[3], ButtonPanel4, 210, 240, 130, 100, true, "Ergo");

        button[4] = new JButton();
        Button1Settings button5Settings = new Button1Settings(button[4], ButtonPanel4, 370, 240, 130, 100, true, "Inne - Gry zespolowe");

        button[5] = new JButton();
        Button1Settings button6Settings = new Button1Settings(button[5], ButtonPanel4, 40, 240, 130, 100, true, "Cofnij");


        /*
        //Sprowadzam date z serwera sql
        Session session = null;

        session = sessionFactory.openSession();
        Date currentDate = (Date) session.createNativeQuery("SELECT current_timestamp").getSingleResult();
        session.close();
        //

        // Convert Date to Calendar
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);

        //Format %02d oznacza, że chcemy wyświetlić liczbę jako łańcuch znaków,
        // z wiodącym zerem, jeśli liczba zajmuje mniej niż 2 cyfry.

        // Extract day, month, year, hour, and minute
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String Day = String.format("%02d", day);

        int month = calendar.get(Calendar.MONTH) + 1; // Months are 0-based, add 1 for human-readable format
        String Month = String.format("%02d", month);

        int year = calendar.get(Calendar.YEAR);
        String Year = Integer.toString(year);


        //
        //System.out.println("Dzisiaj jest " + day + "." + month + "." + year + " a godzinka to " + hour + ":" + minute);

        int idU = 1;
        String date = Day + Month + Year;
        String type = "bieg";
        int km = 4;
        int time = 2;

        Session session2 = sessionFactory.getCurrentSession();
        session2.beginTransaction();

        Workouts workouts = new Workouts(idU, date, type, km, time);
        session2.save(workouts);

        session2.getTransaction().commit();
        System.out.println("Dodano trening o ID: " + workouts.getId());

         */
    }
}
