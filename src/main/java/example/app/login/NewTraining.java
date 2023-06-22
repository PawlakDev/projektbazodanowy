package example.app.login;

import example.app.buttons.Button1Settings;
import org.hibernate.SessionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewTraining extends JFrame {

    JPanel ButtonPanel2, ButtonPanel3; // 2 zeby nie mylic
    JButton[] button = new JButton[6];
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
                        CreatingNewTraining creatingNewTraining = new CreatingNewTraining(sessionFactory, ButtonPanel3, BackgroundImagePanel, to, jLayeredPane);
                        creatingNewTraining.zrob();
                    }
                });


                button[4] = new JButton();
                Button1Settings button4Settings = new Button1Settings(button[4], ButtonPanel3, 20, 130, 130, 100, true, "Wpisz recznie date");

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
