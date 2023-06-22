package example.app.login;

import example.app.InfoFrames.WelcomeMsgSettings;
import example.app.buttons.Button1Settings;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreatingNewTraining {
    JLayeredPane jLayeredPane;
    JFrame to;
    JTextArea jTextArea;
    private JPanel ButtonPanel4, wellcomeMsgPanel, WpisTekstPanel, ButtonPanel3, BackgroundImagePanel;
    private JLabel wellcomeMsgLabel, WpisTekstLabel;
    private final JButton[] button = new JButton[6]; // wartosc do zmiany
    private SessionFactory sessionFactory;

    public CreatingNewTraining(SessionFactory sessionFactory, JPanel ButtonPanel3, JPanel BackgroundImagePanel, JFrame to, JLayeredPane jLayeredPane) {
        this.sessionFactory = sessionFactory;
        this.ButtonPanel3 = ButtonPanel3;
        this.BackgroundImagePanel = BackgroundImagePanel;
        this.to = to;
        this.jLayeredPane = jLayeredPane;
    }

    public CreatingNewTraining() {

    }

    public JLayeredPane getjLayeredPane() {
        return jLayeredPane;
    }

    public JLabel getWellcomeMsgLabel() {
        return wellcomeMsgLabel;
    }

    public JPanel getWellcomeMsgPanel() {
        return wellcomeMsgPanel;
    }

    public JPanel getButtonPanel4() {
        return ButtonPanel4;
    }


    public void zrob() {
        ButtonPanel3.setEnabled(false);
        ButtonPanel3.setVisible(false);


        ButtonPanel4 = new JPanel();
        ButtonPanel4.setBounds(0, 0, 500, 400);
        ButtonPanel4.setLayout(null);
//        //Ustawienie koloru tla
        ButtonPanel4.setBackground(new Color(0, 0, 0, 0));

        wellcomeMsgPanel = new JPanel();
        wellcomeMsgLabel = new JLabel();

        WelcomeMsgSettings welcomeMsgSettings = new WelcomeMsgSettings(wellcomeMsgLabel, wellcomeMsgPanel, "Wybierz typ treningu", 70, 380);

        jLayeredPane.add(wellcomeMsgPanel, JLayeredPane.PALETTE_LAYER); //
        jLayeredPane.add(ButtonPanel4, JLayeredPane.PALETTE_LAYER); //

        button[0] = new JButton();
        button[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTrainingDescriptions opis = new addTrainingDescriptions(sessionFactory,ButtonPanel4, jLayeredPane, wellcomeMsgLabel, wellcomeMsgPanel);

                opis.zrob();

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
        });

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
        button[5].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonPanel4.setEnabled(false);
                ButtonPanel4.setVisible(false);

                wellcomeMsgPanel.setEnabled(false);
                wellcomeMsgPanel.setVisible(false);


                ButtonPanel3.setEnabled(true);
                ButtonPanel3.setVisible(true);


            }
        });
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
