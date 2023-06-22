package example.app.login;

import example.app.InfoFrames.WelcomeMsgSettings;
import example.app.buttons.Button1Settings;
import example.app.dbSettings.Workouts;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import static example.Main.sessionFactory;

public class addTrainingDescriptions {

    JLabel WpisTekstLabel;
    JPanel WpisTekstPanel,ButtonPanel4;
    JTextArea jTextArea;

    JLayeredPane jLayeredPane;
    JLabel wellcomeMsgLabel;
    JPanel wellcomeMsgPanel;
    String type;
    SessionFactory sessionFactory;
    public addTrainingDescriptions(SessionFactory session, JPanel ButtonPanel4, JLayeredPane jLayeredPane, JLabel wellcomeMsgLabel, JPanel wellcomeMsgPanel, String typ){
        this.ButtonPanel4 = ButtonPanel4;
        this.jLayeredPane = jLayeredPane;
        this.wellcomeMsgLabel = wellcomeMsgLabel;
        this.wellcomeMsgPanel = wellcomeMsgPanel;
        this.sessionFactory = session;
        this.type = typ;
    }

    void zrob(){
        CreatingNewTraining creatingNewTraining = new CreatingNewTraining();

        ButtonPanel4.setVisible(false);
        ButtonPanel4.setEnabled(false);

        WpisTekstLabel = new JLabel();
        WpisTekstPanel = new JPanel();
        WpisTekstPanel.setOpaque(false);
        WpisTekstPanel.setBounds(50,120,300,190);

        jLayeredPane.add(WpisTekstPanel, JLayeredPane.PALETTE_LAYER); //

        WelcomeMsgSettings welcomeMsgSettings1 = new WelcomeMsgSettings(wellcomeMsgLabel, wellcomeMsgPanel, "Wpisz Opis", 120, 310 );

        jTextArea = new JTextArea();
        jTextArea.setPreferredSize(new Dimension(300, 180));
        jTextArea.setFont(new Font("MV Boli", 0, 32));
        jTextArea.setForeground(new Color(0, 0, 0, 128));
        jTextArea.setBorder(new LineBorder(Color.BLACK));

        WpisTekstPanel.add(jTextArea);

        JPanel ButtonPanelApply = new JPanel();

        JButton buttonApply2 = new JButton();
        Button1Settings buttonApply2Settings = new Button1Settings(buttonApply2, ButtonPanelApply, 370, 230, 130, 70, true, "Zatwierdz");
        buttonApply2.setEnabled(true);
        buttonApply2.setVisible(true);

        JButton buttonApply3 = new JButton();
        Button1Settings buttonApply3Settings = new Button1Settings(buttonApply3, ButtonPanelApply, 370, 230, 130, 70, false, "Zatwierdz");

        JButton buttonBack = new JButton();

        JButton buttonApply = new JButton();
        Button1Settings buttonApplySettings = new Button1Settings(buttonApply, ButtonPanelApply, 370, 230, 130, 70, true, "Zatwierdz");
        buttonApply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String opis = jTextArea.getText();
                wellcomeMsgLabel.setText("Wpisz ilosc km");
                jTextArea.setText("");
                buttonApply.setEnabled(false);
                buttonApply.setVisible(false);

                buttonApply2.setEnabled(true);
                buttonApply2.setVisible(true);

                buttonApply2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String Km = jTextArea.getText();
                        if(!(Km.matches("-?\\d+(\\.\\d+)?"))) {
                            wellcomeMsgLabel.setText("Wpisany tekst nie jest liczbą");
                        } else {
                            jTextArea.setText("");
                            wellcomeMsgLabel.setText("Wpisz czas treningu");

                            buttonApply2.setVisible(false);
                            buttonApply2.setEnabled(false);

                            buttonApply3.setVisible(true);
                            buttonApply3.setEnabled(true);

                            buttonApply3.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    buttonApply3.setEnabled(false);
                                    buttonApply3.setVisible(false);


                                    String Czas = jTextArea.getText();

                                    //

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


                                    //System.out.println("Dzisiaj jest " + day + "." + month + "." + year );

                                    int idU = 25;
                                    String date = Year + "-" + Month + "-"+ Day ;
                                    int km = Integer.parseInt(Km);
                                    int time = Integer.parseInt(Czas);

                                    Session session2 = sessionFactory.getCurrentSession();
                                    session2.beginTransaction();

                                    Workouts workouts = new Workouts(idU, date, type, km, time, opis);
                                    session2.save(workouts);

                                    session2.getTransaction().commit();
                                    System.out.println("Dodano trening o ID: " + workouts.getId());

                                    //
                                    ButtonPanel4.setVisible(true);
                                    ButtonPanel4.setEnabled(true);

                                    WpisTekstPanel.setVisible(false);
                                    WpisTekstPanel.setEnabled(false);

                                    jTextArea.setEnabled(false);
                                    jTextArea.setVisible(false);

                                    buttonBack.setVisible(false);
                                    buttonBack.setEnabled(false);

                                    buttonApply.setVisible(false);
                                    buttonApply.setEnabled(false);

                                    buttonApply2.setEnabled(false);
                                    buttonApply2.setVisible(false);

                                    buttonApply3.setEnabled(false);
                                    buttonApply3.setVisible(false);

                                    WelcomeMsgSettings welcomeMsgSettings = new WelcomeMsgSettings(wellcomeMsgLabel, wellcomeMsgPanel, "Wybierz typ treningu", 70,380);
                                }
                            });
                        }
                    }
                });
            }
        });

        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonPanel4.setVisible(true);
                ButtonPanel4.setEnabled(true);

                WpisTekstPanel.setVisible(false);
                WpisTekstPanel.setEnabled(false);

                jTextArea.setEnabled(false);
                jTextArea.setVisible(false);

                buttonBack.setVisible(false);
                buttonBack.setEnabled(false);

                buttonApply.setVisible(false);
                buttonApply.setEnabled(false);

                buttonApply2.setEnabled(false);
                buttonApply2.setVisible(false);

                buttonApply3.setEnabled(false);
                buttonApply3.setVisible(false);

                WelcomeMsgSettings welcomeMsgSettings = new WelcomeMsgSettings(wellcomeMsgLabel, wellcomeMsgPanel, "Wybierz typ treningu", 70,380);
            }
        });
        Button1Settings buttonBackSettings = new Button1Settings(buttonBack, ButtonPanelApply, 370, 150, 130, 70, true, "Cofnij");




        buttonApply.setVisible(true);
        buttonApply.setEnabled(true);

        ButtonPanelApply.add(buttonBack);
        ButtonPanelApply.add(buttonApply);
        ButtonPanelApply.add(buttonApply2);
        ButtonPanelApply.add(buttonApply3);
        jLayeredPane.add(buttonApply, JLayeredPane.PALETTE_LAYER); //
        jLayeredPane.add(buttonApply2, JLayeredPane.PALETTE_LAYER); //
        jLayeredPane.add(buttonApply3, JLayeredPane.PALETTE_LAYER); //
        jLayeredPane.add(buttonBack, JLayeredPane.PALETTE_LAYER); //
    }
}
