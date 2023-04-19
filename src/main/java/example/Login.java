package example;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Login extends JFrame {
private JLabel label2;
private JPanel panel3, frameBackground , panel5, Login, Password;
private JTextArea textArea3, textArea4; // textArea2, 1 - login, 4 - funkcja
    JTextArea textLogin;
    private JPasswordField textArea2; //haslo
    private JLayeredPane GraphicFrame;

    Login(JPanel panel, JPanel panel2, JFrame to, JButton jButton, JButton ButtonBack){

        //Wylaczam stare panele
        panel.setEnabled(false);
        panel2.setEnabled(false);
        panel.setVisible(false);
        panel2.setVisible(false);


        //label
        label2 = new JLabel();

        //panel 3 to jest to Zaloguj sie na gorze
        panel3 = new JPanel();
        panel3.setBackground(Color.white);
        WpisywanieTekstu wpisywanieTekstu = new WpisywanieTekstu(label2,panel3, "Zaloguj sie");

        //panel4
        frameBackground = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(getBackground());
                g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
            }
        };

        //zaczelam robic PanelSettings dla czytelnosci, ale nie wiem jeszcze czy sie przyda
        frameBackground.setBackground(new Color(200, 230, 255));
        frameBackground.setBounds(0, 0, 400, 160);
        frameBackground.setOpaque(false);
        frameBackground.setBorder(new LineBorder(new Color(100, 150, 200), 2, true));
        frameBackground.setVisible(true);

        //panel5
        panel5 = new JPanel();
        panel5.setBackground(Color.white);
        panel5.setBounds(60,270,400,80); //135
        panel5.setVisible(true);
        panel5.setLayout(null);


        // ! Panel "login" jest odpowiedzialny za poprawne wyswietlanie pola tekstowego do logowania (jest w nim textArea "textlogin") !

        Login = new JPanel();
        Login.setOpaque(false); // Ta obcja sluzy do ustawienia przezroczystego tla (niegenerowanie tla)
        Login.setBounds(0, 20, 400, 120); // Ustaw wymiary i pozycję
        Login.setVisible(true);

        // ! Panel "password" jest odpowiedzialny za poprawne wyswietlanie pola tekstowego do logowania (jest w nim JPasswordField "textArea2") !

        Password = new JPanel();
        Password.setOpaque(false); // Ta obcja sluzy do ustawienia przezroczystego tla (niegenerowanie tla)
        Password.setBounds(0, 80, 400, 120); // Ustaw wymiary i pozycję
        Password.setVisible(true);

        // Tworzenie układu warstwowego i dodawanie paneli
        GraphicFrame = new JLayeredPane();
        GraphicFrame.setBounds(60, 105, 400, 540);
        GraphicFrame.add(frameBackground, JLayeredPane.DEFAULT_LAYER);
        GraphicFrame.add(Login, JLayeredPane.PALETTE_LAYER);
        GraphicFrame.add(Password, JLayeredPane.PALETTE_LAYER);
        to.add(GraphicFrame);


        //textArea - login
        //textLogin = new JtextArea("login", "login");
        textLogin = new JTextArea("login");

        textLogin.setPreferredSize(new Dimension(210,40));
        textLogin.setBounds(20, 60, 230, 50); // Ustawia współrzędne i rozmiar
        textLogin.setFont(new Font("MV Boli", 0, 32));
        textLogin.setForeground(new Color(0, 0, 0, 128)); // Ustawienie przezroczystości tekstu (128 - półprzezroczysty)
        textLogin.setBorder(new LineBorder(Color.BLACK));

        textLogin.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (textLogin.getText().equals("")) {
                    textLogin.setText("login");
                    textLogin.setForeground(new Color(0, 0, 0, 128));
                }
            }

                @Override
                public void focusGained(FocusEvent e) {
                    if (textLogin.getText().equals("login")) {
                        textLogin.setText("");
                        textLogin.setForeground(new Color(0, 0, 0, 255)); // Ustawienie przezroczystości tekstu
                    }
                }
            });

          //haslo
        textArea2 = new JPasswordField();

        textArea2.setText("haslo");//to powinno sprawiać, że wyświetla ajpierw haslo
        textArea2.setPreferredSize(new Dimension(210,40));
        textArea2.setBounds(20, 60, 210, 40); // Ustawia współrzędne i rozmiar
        textArea2.setFont(new Font("MV Boli", 0, 32));
        textArea2.setForeground(new Color(0, 0, 0, 128)); // Ustawienie przezroczystości tekstu (128 - półprzezroczysty)
        textArea2.setBorder(new LineBorder(Color.BLACK));
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
        ToggleButtonSettings ToggleButtonSettings = new ToggleButtonSettings(showPsw,180,20,100,50);
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
        jButton.setBounds(300, 20, 100, 50);
        jButton.setText("Zaloguj");
        jButton.setFocusable(false);
        jButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 150, 200), 2),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        jButton.setFont(new Font("Arial", Font.BOLD, 14));

        // JButton - Cofnij
        ButtonBack.setBackground(new Color(200, 230, 255));
        ButtonBack.setVisible(true);
        ButtonBack.setLayout(null);
        ButtonBack.setBounds(0, 20, 100, 50);
        ButtonBack.setText("Wstecz");
        ButtonBack.setFocusable(false);
        ButtonBack.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 150, 200), 2),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        ButtonBack.setFont(new Font("Arial", Font.BOLD, 14));

        Login.add(textLogin);
        Password.add(textArea2);

        //panel5 to buttony zaloguj i pokaz haslo
        panel5.add(ButtonBack);
        panel5.add(showPsw);
        panel5.add(jButton);

        //dodaje nowe panele
        to.add(panel3);
        //to.add(panel4);
        to.add(panel5);
    }

    public String getTextLogin() {
            return textLogin.getText();
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

    public JPanel getFrameBackground() {
        return frameBackground;
    }

    public JPanel getPanel5() {
        return panel5;
    }
    public String getTextArea2() {
        char[] passwordChars = textArea2.getPassword();
        String passwordString = new String(passwordChars);
        return passwordString;
    }

    public JLayeredPane getGraphicFrame() {
        return GraphicFrame;
    }

    public JPanel getLogin() {
        return Login;
    }

    public JPanel getPassword() {
        return Password;
    }
}


