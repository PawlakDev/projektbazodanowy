package example;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class MyFrame2 extends JFrame {
JLabel label2;
JPanel panel3, panel4, panel5;
JTextArea textArea3, textArea4; // textArea2, 1 - login, 4 - funkcja
textArea textLogin;
JPasswordField textArea2; //haslo

    MyFrame2(JPanel panel, JPanel panel2, JFrame to, JButton jButton){

        //Wylaczam stare panele
        panel.setVisible(false);
        panel2.setVisible(false);

        //label
        label2 = new JLabel();

        //panel 3 to jest to Zaloguj sie na gorze
        panel3 = new JPanel();
        panel3.setBackground(Color.white);
        WpisywanieTekstu wpisywanieTekstu = new WpisywanieTekstu(label2,panel3, "Zaloguj sie");

        //panel4
        panel4 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(getBackground());
                g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
            }
        };

        //zaczelam robic PanelSettings dla czytelnosci, ale nie wiem jeszcze czy sie przyda
        panel4.setBackground(new Color(200, 230, 255));
        panel4.setBounds(45, 110, 450, 100);
        panel4.setOpaque(false);
        panel4.setBorder(new LineBorder(new Color(100, 150, 200), 2, true));
        panel4.setVisible(true);

        //panel5
        panel5 = new JPanel();
        panel5.setBackground(Color.white);
        panel5.setBounds(80,210,355,80);
        panel5.setVisible(true);
        panel5.setLayout(null);

        //textArea - login
        textLogin = new textArea("login", "");
        //textLogin.setPreferredSize(new Dimension(210,40));
        //textLogin.setFont(new Font("MV Boli", 0, 32));

        textLogin.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                    if(textLogin.getText().equals("")) {
                        textLogin.setText("login");
                        textLogin.setForeground(new Color(0, 0, 0, 128));
                    }
            }
            @Override
            public void focusGained(FocusEvent e) {
                if (textLogin.getText().equals("login")) {
                    textLogin.setText("");
                    textLogin.setForeground(new Color(0, 0, 0, 255)); // Ustawienie przezroczystości tekstu - nieprzezroczysty
                }
            }
        });

        //haslo
        textArea2 = new JPasswordField();

        textArea2.setText("haslo");//to powinno sprawiać, że wyświetla ajpierw haslo
        textArea2.setPreferredSize(new Dimension(210,40));
        textArea2.setFont(new Font("MV Boli", 0, 32));
        textArea2.setForeground(new Color(0, 0, 0, 128)); // Ustawienie przezroczystości tekstu (128 - półprzezroczysty)
        textArea2.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if(textArea2.getText().equals("")) {
                    textArea2.setText("haslo");
                    textArea2.setForeground(new Color(0, 0, 0, 128)); // Ustawienie przezroczystości tekstu (128 - półprzezroczysty)
                }
            }
            @Override
            public void focusGained(FocusEvent e) {
                if (textArea2.getText().equals("haslo")) {
                    textArea2.setText("");
                    textArea2.setForeground(new Color(0, 0, 0, 255)); // Ustawienie przezroczystości tekstu
                }
            }
        });

        //button pokaz / ukryj haslo
        JToggleButton showPsw = new JToggleButton("pokaz haslo");
        ToggleButtonSettings ToggleButtonSettings = new ToggleButtonSettings(showPsw,20,20,100,50);
        showPsw.setFont(new Font("Arial", Font.BOLD, 14));

        showPsw.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 150, 200), 2),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        showPsw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showPsw.isSelected()) {
                    showPsw.setText("ukryj haslo");
                    textArea2.setEchoChar((char) 0);
                } else {
                    showPsw.setText("pokaz haslo");
                    textArea2.setEchoChar('*');
                }
            }
        });

        // JButton - Zaloguj
        jButton.setBackground(new Color(200, 230, 255));
        jButton.setVisible(true);
        jButton.setLayout(null);
        jButton.setBounds(140, 20, 100, 50);
        jButton.setText("Zaloguj");
        jButton.setFocusable(false);
        jButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 150, 200), 2),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        jButton.setFont(new Font("Arial", Font.BOLD, 14));

        //dodaje pola tekstowe i buttony do paneli

        //panel4 to pola tekstowe haslo i login
        panel4.add(textLogin);
        panel4.add(textArea2);

        //panel5 to buttony zaloguj i pokaz haslo
        panel5.add(showPsw);
        panel5.add(jButton);

        //dodaje nowe panele
        to.add(panel3);
        to.add(panel4);
        to.add(panel5);
    }

    public String getTextLogin() {
            return textLogin.getTextHolder();
    }

    public JTextArea getTextArea3(){
        return textArea3;
    }

    public JTextArea getTextArea4(){
        return textArea4;
    }
    public JLabel getLabel2() {
        return label2;
    }

    public JPanel getPanel3() {
        return panel3;
    }

    public JPanel getPanel4() {
        return panel4;
    }

    public JPanel getPanel5() {
        return panel5;
    }
    public String getTextArea2() {
        char[] passwordChars = textArea2.getPassword();
        String passwordString = new String(passwordChars);
        return passwordString;
    }
}
