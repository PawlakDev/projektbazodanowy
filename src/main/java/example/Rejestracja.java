package example;

import example.InfoFrames.LoginInfoFrameSettings;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.List;


public class Rejestracja extends JFrame {
    JLabel labelTytul;
    JPanel panelTytul;
    JPanel DownButtonPanel;

    JPanel frameBackground;
    JPanel panel5;
    JPanel Login;
    JPanel Password;
    JTextArea textLogin;

    JButton next, ButtonBack;
    private JPasswordField password;
    private JLayeredPane GraphicFrame;




    Rejestracja(SessionFactory sessionFactory, JFrame to, JPanel ButtonPanel, JPanel BackgroundImagePanel){


        System.out.println("rejestracja");
        this.DownButtonPanel = new JPanel();
        //Wylaczam stare panele
        ButtonPanel.setEnabled(false);
        BackgroundImagePanel.setEnabled(false);
        ButtonPanel.setVisible(false);
        BackgroundImagePanel.setVisible(false);

        labelTytul = new JLabel();
        panelTytul = new JPanel();
        panelTytul.setBackground(Color.white);
        LoginInfoFrameSettings loginInfoFrameSettings = new LoginInfoFrameSettings(labelTytul,panelTytul, "Zarejestruj sie");

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
        textLogin = new JTextArea("login");

        textLogin.setPreferredSize(new Dimension(210,40));
        textLogin.setBounds(70, 60, 230, 50); // Ustawia współrzędne i rozmiar
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
        password.setBounds(70, 60, 210, 40); // Ustawia współrzędne i rozmiar
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

        ButtonBack = new JButton();
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

        ButtonBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == ButtonBack) {
                    System.out.println("ELO ELOOOO");
                }

                labelTytul.setText("Zarejestruj sie");
                getPanel5().setVisible(false);
                getGraphicFrame().setVisible(false);
                Login.setVisible(false);
                Password.setVisible(false);
                ButtonPanel.setVisible(true);
                BackgroundImagePanel.setVisible(true);
                frameBackground.setVisible(false);
            }
        });

        // next - powinno przekierowac na rejestracjadane
        next = new JButton(); //panele do pozmieniania bo sa crazy
        next.setText("dalej"); //ustawienie tego buttona chyba nie istniej

        next.setBackground(new Color(200, 230, 255));
        next.setVisible(true);
        next.setLayout(null);
        next.setBounds(300, 20, 100, 50);
        next.setText("dalej");
        next.setFocusable(false);
        next.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 150, 200), 2),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        next.setFont(new Font("Arial", Font.BOLD, 14));

        final boolean[] ok = {true}; // zmienna pomocnicza "tyczasowa" bo nie wiem jaki warunek dac do else if
        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // tu sprawdzam czy login i haslo zostalo uzupelnione
                if (textLogin.getText().equals("login") || textLogin.getText().equals("") || password.getText().equals("") || password.getText().equals("haslo"))
                    labelTytul.setText("Bledne dane!");
                else{
                    // tu do sprawdzania w bazie czu juz istnieje login
                    Session session = null;
                    Transaction transaction = null;
                    try {
                        session = sessionFactory.getCurrentSession();
                        transaction = session.beginTransaction();
                        String text = textLogin.getText();
                        System.out.println(text);
                        List<User> users = session.createQuery("from User", User.class).getResultList();
                        for (User user : users) {
                            if (user.getUsername().equals(text)) {
                                labelTytul.setText("Podany login juz istnieje");
                                ok[0] = false;
                            }
                        }
                        transaction.commit();
                    } catch (HibernateException ex) {
                        if (transaction != null) {
                            transaction.rollback();
                        }
                        throw new RuntimeException(ex);
                    } finally {
                        if (session != null) {
                            session.close();
                        }
                    }
                    // poprawne dane do loginu i hasla - przechodze do kolejnego okna
                    if (ok[0] == true) {
                        labelTytul.setVisible(false);
                        showPsw.setVisible(false);
                        showPsw.setEnabled(false);

                        // Getterów pouzywać
                        RejestracjaData rejestracjaData = new RejestracjaData(to, panelTytul, Login, Password, frameBackground, ButtonBack, next, textLogin, password);
                    }
                }
            }
        });
        Login.add(textLogin);
        Password.add(password);

        GraphicFrame = new JLayeredPane();
        GraphicFrame.setBounds(60, 105, 400, 540);
        GraphicFrame.add(frameBackground, JLayeredPane.DEFAULT_LAYER);
        GraphicFrame.add(Login, JLayeredPane.PALETTE_LAYER);
        GraphicFrame.add(Password, JLayeredPane.PALETTE_LAYER);
        to.add(GraphicFrame);

        //panel5 to buttony zaloguj i pokaz haslo
        panel5.add(ButtonBack);
        panel5.add(showPsw);
        panel5.add(next);

        //dodaje nowe panele
        to.add(panelTytul);
        to.add(panel5);
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
    public void setFrameBackground(JPanel frameBackground) {
        this.frameBackground = frameBackground;
    }

    public String getPassword()
    {
        char[] passwordChars = password.getPassword();
        String passwordString = new String(passwordChars);
        return passwordString;
    }
    public JButton getNext() {
        return next;
    }
}

