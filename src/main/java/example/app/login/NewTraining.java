package example.app.login;

import example.app.InfoFrames.WelcomeMsgSettings;
import example.app.buttons.Button1Settings;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Calendar;
import java.util.Date;

public class NewTraining extends JFrame {

    JPanel ButtonPanel2, ButtonPanel3; // 2 zeby nie mylic
    JButton[] button = new JButton[6];
    JLabel wellcomeMsgLabel;
    JPanel wellcomeMsgPanel;
    /*
     * Buttons:
     * 0 - Wczytaj z garmina
     * 1 - Wpisz recznie
     * 2 - Cofnij
     *  3 - Aktualna data
     *  4 - Wpisz recznie
     *  5 - Cofnij
     */
    SessionFactory sessionFactory;

    public NewTraining(JPanel panel, JPanel panel2, JFrame to, SessionFactory sessionFactory, JPanel BackgroundImagePanel, JLayeredPane jLayeredPane) {

        this.sessionFactory = sessionFactory;

        panel.setEnabled(false);
        panel.setVisible(false);

        ButtonPanel2 = new JPanel();
        ButtonPanel2.setBounds(0, 0, 180, 400);
        ButtonPanel2.setLayout(null);
        //Ustawienie koloru tla
        ButtonPanel2.setBackground(Color.white);
        to.add(ButtonPanel2);

        button[0] = new JButton();

        Button1Settings button1Settings = new Button1Settings(button[0], ButtonPanel2, 20, 20, 130, 100, false, "Wczytaj z garmina");
        button[1] = new JButton();
        button[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonPanel2.setEnabled(false);
                ButtonPanel2.setVisible(false);

                ButtonPanel3 = new JPanel();

                ButtonPanel3 = new JPanel();
                ButtonPanel3.setBounds(0, 0, 180, 400);
                ButtonPanel3.setLayout(null);
                //Ustawienie koloru tla
                ButtonPanel3.setBackground(Color.white);
                to.add(ButtonPanel3);

                button[3] = new JButton();
                Button1Settings button3Settings = new Button1Settings(button[3], ButtonPanel3, 20, 20, 130, 100, true, "Aktualna Data");

                button[3].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        CreatingNewTraining creatingNewTraining = new CreatingNewTraining(sessionFactory, ButtonPanel3, BackgroundImagePanel, to, jLayeredPane,"0","0","0");
                        creatingNewTraining.zrob();
                    }
                });


                button[4] = new JButton();
                Button1Settings button4Settings = new Button1Settings(button[4], ButtonPanel3, 20, 130, 130, 100, true, "Wpisz recznie date");
                button[4].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                           ButtonPanel2.setVisible(false);
                           ButtonPanel2.setEnabled(false);
                           ButtonPanel3.setVisible(false);
                            ButtonPanel3.setEnabled(false);

                            wellcomeMsgLabel = new JLabel();
                        wellcomeMsgPanel = new JPanel();
                        JPanel wpisTekstPanel = new JPanel();
                        wpisTekstPanel.setOpaque(false);
                        wpisTekstPanel.setBounds(50,120,300,190);

                        JPanel ButtonPanel = new JPanel();
                        ButtonPanel.setBounds(0, 0, to.getWidth(), to.getHeight());
                        ButtonPanel.setLayout(null);
                        //Ustawienie koloru tla
                        ButtonPanel.setBackground(Color.white);
                        to.add(ButtonPanel);


                        WelcomeMsgSettings welcomeMsgSettings = new WelcomeMsgSettings(wellcomeMsgLabel, wellcomeMsgPanel, "Wpisz Date", 120, 310 );


                        JButton buttonBack = new JButton();
                        //action listener
                        buttonBack.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                ButtonPanel.setVisible(false);
                                ButtonPanel.setEnabled(false);
                                wellcomeMsgPanel.setVisible(false);
                                wellcomeMsgPanel.setEnabled(false);
                                wpisTekstPanel.setVisible(false);
                                wpisTekstPanel.setEnabled(false);
                                buttonBack.setVisible(false);
                                buttonBack.setEnabled(false);
                                panel.setVisible(true);
                                panel.setEnabled(true);
                            }
                        });
                        Button1Settings buttonBackSettings = new Button1Settings(buttonBack, ButtonPanel, 370, 230, 130, 70, true, "Cofnij");

                        buttonBack.setVisible(true);
                        buttonBack.setEnabled(true);

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



                        //System.out.println("Dzisiaj jest " + day + "." + month + "." + year   );



                        JTextArea jTextArea= new JTextArea();
                        jTextArea.setPreferredSize(new Dimension(300, 180));
                        jTextArea.setFont(new Font("MV Boli", 0, 32));
                        jTextArea.setForeground(new Color(0, 0, 0, 128));
                        jTextArea.setBorder(new LineBorder(Color.BLACK));
                        jTextArea.setText(Day + "." + Month + "." + Year);


                        jTextArea.addFocusListener(new FocusAdapter() {
                            @Override
                            public void focusLost(FocusEvent e) {
                                if (jTextArea.getText().equals("")) {
                                    jTextArea.setText(Day + "." + Month + "." + Year);
                                    jTextArea.setForeground(new Color(0, 0, 0, 128));
                                }
                            }

                            @Override
                            public void focusGained(FocusEvent e) {
                                if (jTextArea.getText().equals(Day + "." + Month + "." + Year)) {
                                    jTextArea.setText("");
                                    jTextArea.setForeground(new Color(0, 0, 0, 255)); // Ustawienie przezroczystości tekstu
                                }
                            }
                        });

                        JButton buttonApply = new JButton();
                        //Po kliknięciu buttonApply
                        buttonApply.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String dzien = jTextArea.getText().substring(0,2);
                                String miesiac = jTextArea.getText().substring(3,5);
                                String rok = jTextArea.getText().substring(6,10);

                                System.out.println(dzien + " " + miesiac + " " + rok);

                                //Wyłączam panele
                                ButtonPanel.setVisible(false);
                                ButtonPanel.setEnabled(false);
                                wellcomeMsgPanel.setVisible(false);
                                wellcomeMsgPanel.setEnabled(false);
                                wpisTekstPanel.setVisible(false);
                                wpisTekstPanel.setEnabled(false);
                                buttonBack.setVisible(false);
                                buttonBack.setEnabled(false);

                                CreatingNewTraining creatingNewTraining = new CreatingNewTraining(sessionFactory, ButtonPanel3, BackgroundImagePanel, to, jLayeredPane, dzien, miesiac, rok);
                                creatingNewTraining.zrob();
                            }
                        });
                        Button1Settings button1Settings = new Button1Settings(buttonApply, ButtonPanel, 370, 150, 130, 70, true, "Zatwierdz");

                        wpisTekstPanel.add(jTextArea);
                        jLayeredPane.add(wellcomeMsgPanel, JLayeredPane.PALETTE_LAYER); //
                        jLayeredPane.add(wpisTekstPanel, JLayeredPane.PALETTE_LAYER); //
                        ButtonPanel.add(buttonApply);
                        ButtonPanel.add(buttonBack);
                        jLayeredPane.add(ButtonPanel, JLayeredPane.PALETTE_LAYER); //
                    }
                });


                button[5] = new JButton();
                Button1Settings button5Settings = new Button1Settings(button[5], ButtonPanel3, 20, 240, 130, 100, true, "Cofnij");

                button[5].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ButtonPanel3.setEnabled(false);
                        ButtonPanel3.setVisible(false);

                        ButtonPanel2.setEnabled(true);
                        ButtonPanel2.setVisible(true);
                    }
                });
            }
        });
        Button1Settings button2Settings = new Button1Settings(button[1], ButtonPanel2, 20, 130, 130, 100, true, "Wpisz recznie");
        button[2] = new JButton();
        button[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonPanel2.setVisible(false);
                ButtonPanel2.setEnabled(false);

                panel2.setVisible(true);
                panel2.setEnabled(true);

                panel.setEnabled(true);
                panel.setVisible(true);

            }
        });
        Button1Settings button3Settings = new Button1Settings(button[2], ButtonPanel2, 20, 240, 130, 100, true, "Cofnij");
    }

}
