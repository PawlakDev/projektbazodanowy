package example;

import example.InfoFrames.LoginInfoFrameSettings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

//w tej klasie ma byc to co po nacisnieciu next w rejestracni, beda tu dalsze dane do uzupelnienia
public class RejestracjaData extends JFrame{
    JLabel labelTytul;
    JPanel panelTytul, panel, DownButtonPanel, frameBackground, panel5,BackgroundImagePanel;
    textArea emailText;
    JTextArea email;
    private JPasswordField password;
    private JLayeredPane GraphicFrame;
    RejestracjaData(){
        setLayout(new BorderLayout());

        //ustawiam label
        labelTytul = new JLabel();

        //i ButtonPanel do niego
        panelTytul = new JPanel();
        panelTytul.setBackground(Color.white);
        //xd
        LoginInfoFrameSettings wpisywanieTekstu = new LoginInfoFrameSettings(labelTytul,panelTytul, "Uzupelnij dane");

        panel = new JPanel();
        //Ustawianie jego wielkosci
        panel.setBounds(4,100,500,400); //to jest tn bialy tlo
        //Ustawienie koloru tla
        panel.setBackground(Color.white);

        //Tworzenie drugiego panelu
        BackgroundImagePanel = new JPanel();
        //Ustawienie wielkości pamietajac o wielkosciach pierwszego panelu
        BackgroundImagePanel.setBounds(160,-25,370,400);

        //to jest to co nie dziala
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

        panel.add(emailText);
        panel.add(email);

        panel.setVisible(true);
        BackgroundImagePanel.setVisible(true);
        panelTytul.setVisible(true);

        this.add(panelTytul);
        this.add(panel);
        this.add(BackgroundImagePanel);

        //na sztywno poki c ale trzeba zmienna zrobic
        setSize(550, 400);

        //aby zamykanie dzialalo
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //robie visible frama
        setVisible(true);
    }
}
