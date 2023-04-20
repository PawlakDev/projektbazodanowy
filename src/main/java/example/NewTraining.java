package example;

import example.buttons.Button1Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewTraining extends JFrame {

    JPanel ButtonPanel2; // 2 zeby nie mylic
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

    public NewTraining(JPanel panel, JPanel panel2, JFrame to) {
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
                // kod, który ma zostać wykonany po kliknięciu przycisku "Wpisz recznie"
                ButtonPanel2.removeAll();

                button[3] = new JButton();
                Button1Settings button3Settings = new Button1Settings(button[3], ButtonPanel2, 20, 20, 130, 100, true, "Aktualna Data");

                button[4] = new JButton();
                Button1Settings button4Settings = new Button1Settings(button[4], ButtonPanel2, 20, 130, 130, 100, true, "Wpisz recznie date");

                button[5] = new JButton();
                Button1Settings button5Settings = new Button1Settings(button[5], ButtonPanel2, 20, 240, 130, 100, true, "Cofnij");
            }
        });
        Button1Settings button2Settings = new Button1Settings(button[1], ButtonPanel2, 20, 130, 130, 100, true, "Wpisz recznie");
        button[2] = new JButton();
        Button1Settings button3Settings = new Button1Settings(button[2], ButtonPanel2, 20, 240, 130, 100, true, "Cofnij");
    }

}
