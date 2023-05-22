package example;

import example.InfoFrames.LoginInfoFrameSettings;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

//w tej klasie ma byc to co po nacisnieciu next w rejestracni, beda tu dalsze dane do uzupelnienia
public class RejestracjaData extends JFrame{
    JLabel labelTytul;
    JPanel Email;
    textArea emailText;
    boolean athlete;
    JTextArea email;
    private JLayeredPane GraphicFrame;

    RejestracjaData(JFrame to, JPanel panelTytul, JPanel Login, JPanel Password, JPanel frameBackground, JButton ButtonBack){

        System.out.println("rejestracja2");
        //Wylaczam stare panele
        Login.setVisible(false);
        Login.setVisible(false);
        Password.setVisible(false);
        Password.setVisible(false);

        //ustawiam label
        labelTytul = new JLabel();
        LoginInfoFrameSettings wpisywanieTekstu = new LoginInfoFrameSettings(labelTytul,panelTytul, "Uzupelnij dane");

        Email = new JPanel();
        Email.setOpaque(false); // Ta obcja sluzy do ustawienia przezroczystego tla (niegenerowanie tla)
        Email.setBounds(-40, 20, 400, 120); // Ustaw wymiary i pozycję
        Email.setVisible(true);

        //to jest to co nie dzialaten text area do zmiany
        //emailText = new textArea("email", "");
        
        // klasyczne pole tekstowe
        email = new JTextArea("email", 1, 20);
        email.setPreferredSize(new Dimension(10,12));
        email.setFont(new Font("MV Boli", 0, 16));
        email.setBorder(new LineBorder(Color.BLACK));
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
        
        // ToggleButton do zaznaczania czy zawodnik czy trener
        JToggleButton changeFunct = new JToggleButton("zawodnik");
        ToggleButtonSettings ToggleButtonSettings = new ToggleButtonSettings(changeFunct,   310,20,70,40);
        changeFunct.setFont(new Font("Arial", Font.BOLD, 10));

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


        //button cofania updated
        ButtonBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == ButtonBack) {
                    System.out.println("ELO ELOOOO");
                }

                labelTytul.setText("Zarejestruj sie");
                changeFunct.setVisible(false);
                changeFunct.setEnabled(false);
                email.setVisible(false);
                email.setEnabled(false);
            }
        });
        
        Email.add(email); // jeden z tych emaili trzeba usunac

        Email.setVisible(true);
        panelTytul.setVisible(true);

        GraphicFrame = new JLayeredPane();
        GraphicFrame.setBounds(60, 105, 400, 540);
        GraphicFrame.add(frameBackground, JLayeredPane.DEFAULT_LAYER);
        GraphicFrame.add(Email, JLayeredPane.PALETTE_LAYER);
        GraphicFrame.add(changeFunct, JLayeredPane.PALETTE_LAYER);

        to.add(panelTytul);
        to.add(GraphicFrame);
    }
}
