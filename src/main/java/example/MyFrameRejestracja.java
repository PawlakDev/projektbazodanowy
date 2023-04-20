package example;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class MyFrameRejestracja extends JFrame {
    JLabel labelTytul;
    JPanel panelTytul, DownButtonPanel, frameBackground, panel5, Login, Password; //idk co to ten 5 najwyzej usune
    JTextArea textLogin;
    private JPasswordField password;
    private JLayeredPane GraphicFrame;

    textArea emailText;
    MyFrameRejestracja(JPanel ButtonPanel, JPanel BackgroundImagePanel, JButton next, JButton ButtonBack){
        //bylo i to jest jakny do tego co next wiec musze sie pobawic panelami
        /* setLayout(new BorderLayout());

        //ustawiam label
        labelTytul = new JLabel();

        //i ButtonPanel do niego
        panelTytul = new JPanel();
        panelTytul.setBackground(Color.white);
        WpisywanieTekstu wpisywanieTekstu = new WpisywanieTekstu(labelTytul,panelTytul, "Uzupelnij dane");

        panel = new JPanel();
        //Ustawianie jego wielkosci
        panel.setBounds(4,100,500,400); //to jest tn bialy tlo
        //Ustawienie koloru tla
        panel.setBackground(Color.white);

        //Tworzenie drugiego panelu
        BackgroundImagePanel = new JPanel();
        //Ustawienie wielkości pamietajac o wielkosciach pierwszego panelu
        BackgroundImagePanel.setBounds(160,-25,370,400);

        emailText = new textArea("email", "");

        panel.add(emailText);

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
        */

        //skopiowane
        //Wylaczam stare panele
        ButtonPanel.setEnabled(false);
        BackgroundImagePanel.setEnabled(false);
        ButtonPanel.setVisible(false);
        BackgroundImagePanel.setVisible(false);

        //next.addActionListener(to); //idk
        next.setText("dalej"); //ustawienie tego buttona chyba nie istnieje
        DownButtonPanel.add(next); //inny panel
        labelTytul.setText("Zarejestruj sie");

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


        //Panel "signup" jest odpowiedzialny za poprawne wyswietlanie pola tekstowego do logowania (jest w nim textArea "textlogin") !

        Login = new JPanel();
        Login.setOpaque(false); // Ta obcja sluzy do ustawienia przezroczystego tla (niegenerowanie tla)
        Login.setBounds(0, 20, 400, 120); // Ustaw wymiary i pozycję
        Login.setVisible(true);

        //Panel "password" jest odpowiedzialny za poprawne wyswietlanie pola tekstowego do logowania
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
        this.add(GraphicFrame);


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
        password = new JPasswordField();

        password.setText("haslo");//to powinno sprawiać, że wyświetla ajpierw haslo
        password.setPreferredSize(new Dimension(210,40));
        password.setBounds(20, 60, 210, 40); // Ustawia współrzędne i rozmiar
        password.setFont(new Font("MV Boli", 0, 32));
        password.setForeground(new Color(0, 0, 0, 128)); // Ustawienie przezroczystości tekstu (128 - półprzezroczysty)
        password.setBorder(new LineBorder(Color.BLACK));
        password.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if(password.getText().equals("")) {
                    password.setText("haslo");
                    password.setForeground(new Color(0, 0, 0, 128)); // Ustawienie przezroczystości tekstu (128 - półprzezroczysty)
                }
            }
            @Override
            public void focusGained(FocusEvent e) {
                if (password.getText().equals("haslo")) {
                    password.setText("");
                    password.setForeground(new Color(0, 0, 0, 255)); // Ustawienie przezroczystości tekstu
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
                    password.setEchoChar((char) 0);
                } else {
                    showPsw.setText("pokaz haslo");
                    password.setEchoChar('*');
                }
            }
        });

        // next - Zaloguj
        next.setBackground(new Color(200, 230, 255));
        next.setVisible(true);
        next.setLayout(null);
        next.setBounds(300, 20, 100, 50);
        next.setText("Zaloguj");
        next.setFocusable(false);
        next.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 150, 200), 2),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        next.setFont(new Font("Arial", Font.BOLD, 14));

        // next - Cofnij
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
        Password.add(password);

        //panel5 to buttony zaloguj i pokaz haslo
        panel5.add(ButtonBack);
        panel5.add(showPsw);
        panel5.add(next);

        //dodaje nowe panele
        //to.add(panel3);
        //to.add(panel4);
        this.add(panel5);
    }

    public String getTextLogin() {
        return textLogin.getText();
    }

    public JPanel getFrameBackground() {
        return frameBackground;
    }

    public JPanel getPanel5() {
        return panel5;
    }
    public String getpassword() {
        char[] passwordChars = password.getPassword();
        String passwordString = new String(passwordChars);
        return passwordString;
    }

    public JLayeredPane getGraphicFrame() {
        return GraphicFrame;
    }

    public JPanel getLogin() {
        return Login;
    }

    public JLabel getLabelTytul() {
        return labelTytul;
    }


    public String getPassword()
    {
        char[] passwordChars = password.getPassword();
        String passwordString = new String(passwordChars);
        return passwordString;
    }
}

