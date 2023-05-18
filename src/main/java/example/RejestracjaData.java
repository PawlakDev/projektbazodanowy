package example;

import example.InfoFrames.LoginInfoFrameSettings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

//w tej klasie ma byc to co po nacisnieciu next w rejestracni, beda tu dalsze dane do uzupelnienia
public class RejestracjaData extends JFrame{
    JLabel labelTytul;
    JPanel panel2, panel;
    textArea emailText;
    boolean athlete;
    JTextArea email;

    RejestracjaData(JFrame to, JPanel panelTytul, JPanel Login, JPanel Password){

        //Wylaczam stare panele
        Login.setVisible(false);
        Login.setVisible(false);
        Password.setVisible(false);
        Password.setVisible(false);

        //ustawiam label
        labelTytul = new JLabel();
        LoginInfoFrameSettings wpisywanieTekstu = new LoginInfoFrameSettings(labelTytul,panelTytul, "Uzupelnij dane");

        panel = new JPanel();
        panel.setOpaque(false); // Ta obcja sluzy do ustawienia przezroczystego tla (niegenerowanie tla)
        panel.setBounds(20, 120, 400, 120); // Ustaw wymiary i pozycję
        panel.setVisible(true);

        //to jest to co nie dzialaten text area do zmiany
        emailText = new textArea("email", "");

        //brzydkie ale dziala
        // zrobione na razie byle bylo
        email = new JTextArea("email", 1, 20);
        email.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (email.getText().equals("")) {
                    email.setText("email");
                    email.setForeground(new Color(0, 0, 0, 128));
                }
            }

            @Override
            public void focusGained(FocusEvent e) {
                if (email.getText().equals("email")) {
                    email.setText("");
                    email.setForeground(new Color(0, 0, 0, 255)); // Ustawienie przezroczystości tekstu
                }
            }
        });

        // juz mi sie mieszaja te panele wiec jest chaos
        panel2 = new JPanel();
        //Ustawianie jego wielkosci
        panel2.setBounds(10,120,300,100);
        panel2.setOpaque(false); // Ta obcja sluzy do ustawienia przezroczystego tla (niegenerowanie tla)
        panel2.setBounds(20, 120, 400, 120); // Ustaw wymiary i pozycję
        panel2.setVisible(true);

        JToggleButton changeFunct = new JToggleButton("zawodnik");
        ToggleButtonSettings ToggleButtonSettings = new ToggleButtonSettings(changeFunct,   100,20,80,50);
        changeFunct.setFont(new Font("Arial", Font.BOLD, 14));

        changeFunct.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 150, 200), 2),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        changeFunct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (changeFunct.isSelected()) {
                    changeFunct.setText("zawodnik");
                    athlete = true;

                } else {
                    changeFunct.setText("trener");
                    athlete = false;
                }
            }
        });
        changeFunct.setVisible(true);

        panel2.add(changeFunct);
        //panel.add(emailText);
        panel.add(email); // jeden z tych emaili trzeba usunac

        panel.setVisible(true);
        panel2.setVisible(true);
        panelTytul.setVisible(true);

        to.add(panelTytul);
        to.add(panel);
        to.add(panel2);

    }
}
