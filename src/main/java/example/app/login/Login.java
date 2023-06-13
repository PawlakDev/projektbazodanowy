package example.app.login;

import example.app.InfoFrames.WelcomeMsgSettings;
import example.app.Start;
import example.app.ToggleButtonSettings;
import example.app.buttons.Button1Settings;
import example.app.buttons.Button2Settings;
import example.app.dbSettings.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.mindrot.jbcrypt.BCrypt;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.List;

public class Login extends JFrame {
    private JLabel label2;
    private JPanel panel3, frameBackground , panel5, Login, Password;
    private JTextArea textArea3, textArea4; // textArea2, 1 - login, 4 - funkcja
    JTextArea textLogin;
    private JPasswordField textArea2; //haslo
    private JLayeredPane GraphicFrame;

    JPanel BackgroundImagePanel,ButtonPanel,WelcomeMsgPanel;
    JFrame jFrame ;
    JButton jButton,buttonBack;
    JButton[] button ;
    SessionFactory sessionFactory;
    JLabel welcomeMsgLabel;


    public Login(JFrame jFrame, JButton jButton, JButton ButtonBack, JButton button[], SessionFactory session, JPanel BackgroundImagePanel,
                 JPanel ButtonPanel, JPanel WelcomeMsgPanel , JLabel welcomeMsgLabel){
        this.jFrame = jFrame;
        this.jButton = jButton;
        this.buttonBack = ButtonBack;
        this.button = button;
        this.sessionFactory=session;
        this.BackgroundImagePanel = BackgroundImagePanel;
        this.ButtonPanel = ButtonPanel;
        this.WelcomeMsgPanel = WelcomeMsgPanel;
        this.welcomeMsgLabel = welcomeMsgLabel;
    }

    public void zrob(){

        Start start = new Start();

        //Wylaczam stare panele
        start.getButtonPanel().setEnabled(false);
        start.getButtonPanel().setVisible(false);
        start.getBackgroundImagePanel().setEnabled(false);
        start.getBackgroundImagePanel().setVisible(false);


        //label
        label2 = new JLabel();

        //panel3 to jest to Zaloguj sie na gorze
        panel3 = new JPanel();
        panel3.setBackground(Color.white);
        LoginInfoFrameSettings loginInfoFrameSettings = new LoginInfoFrameSettings(label2,panel3, "Zaloguj sie");

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
        jFrame.add(GraphicFrame);


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

        jButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Session session = null;
                Transaction transaction = null;
                try {
                    session = sessionFactory.getCurrentSession();
                    transaction = session.beginTransaction();
                    boolean isFound = false;
                    String text = textLogin.getText();

                    char[] passwordChars = textArea2.getPassword();
                    String passwordString = new String(passwordChars);

                    String text2 = new String(passwordString);
                    System.out.println(text);
                    List<User> users = session.createQuery("from User", User.class).getResultList();
                    for (User user : users) {
                        if (user.getUsername().equals(text)) {

                            boolean passwordMatches = BCrypt.checkpw(text2, user.getPassword());
                            if (passwordMatches) {
                                session.getTransaction().commit();

                                User currentUser = user;

                                //fajnie aby wyswielalo zalogowano przez 2 sekundy
                                panel3.setEnabled(false);
                                panel3.setVisible(false);
                                frameBackground.setEnabled(false);
                                frameBackground.setVisible(false);
                                panel5.setEnabled(false);
                                panel5.setVisible(false);

                                BackgroundImagePanel.setEnabled(true);
                                BackgroundImagePanel.setVisible(true);

                                Login.setVisible(false);
                                Login.setEnabled(false);
                                Password.setVisible(false);
                                Password.setEnabled(false);

                                ButtonPanel.removeAll();
                                ButtonPanel.setEnabled(true);
                                ButtonPanel.setVisible(true);

                                //Button3 - "Nowy trening"
                                button[3] = new JButton();
                                button[3].addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        // kod, który ma zostać wykonany po kliknięciu przycisku "Nowy trening"
                                        WelcomeMsgPanel.setVisible(false);
                                        WelcomeMsgPanel.setEnabled(false);
                                        NewTraining newTraining = new NewTraining(ButtonPanel,WelcomeMsgPanel, jFrame,sessionFactory, BackgroundImagePanel, start.getjLayeredPane());
                                    }
                                });
                                Button1Settings button1Settings = new Button1Settings(button[3], ButtonPanel, 20, 20, 130, 100, true, "Nowy trening");

                                //Button4 - "statystyki"
                                button[4] = new JButton();
                                button[4].addActionListener(this);
                                button[4].addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        // kod, który ma zostać wykonany po kliknięciu przycisku "pokaż statystyki"
                                        WelcomeMsgPanel.setVisible(false);
                                        WelcomeMsgPanel.setEnabled(false);
                                        System.out.println("Pokaż statystyki");
                                    }
                                });
                                Button2Settings button2Settings = new Button2Settings(button[4], ButtonPanel, 20, 130, 130, 100, "Pokaż statystyki");

                                //Button5 - "Wyswietl treningi"
                                button[5] = new JButton();
                                button[5].addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        // kod, który ma zostać wykonany po kliknięciu przycisku "pokaż treningi"
                                        WelcomeMsgPanel.setVisible(false);
                                        WelcomeMsgPanel.setEnabled(false);
                                        ShowTrainings showTrainings = new ShowTrainings(sessionFactory, jFrame, ButtonPanel,currentUser );
                                    }
                                });
                                Button2Settings button3Settings = new Button2Settings(button[5], ButtonPanel, 20, 240, 130, 100, "Pokaż treningi");


                                //Panel
                                WelcomeMsgPanel = new JPanel(){
                                    @Override
                                    protected void paintComponent(Graphics g) {
                                        // Ustaw kolor tła na przezroczysty
                                        g.setColor(new Color(255, 255, 255, 0));
                                        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
                                    }
                                };


                                welcomeMsgLabel = new JLabel();

                                WelcomeMsgSettings welcomeMsgSettings = new WelcomeMsgSettings(welcomeMsgLabel,WelcomeMsgPanel,  "Witaj " + user.getUsername(),180, 330 );

                                start.getjLayeredPane().add(WelcomeMsgPanel, JLayeredPane.PALETTE_LAYER); //

                                jFrame.remove(BackgroundImagePanel);
                                jFrame.remove(ButtonPanel);


                                //session.getTransaction().commit();
                                break;
                            } else {
                                label2.setText("Bledne haslo !");
                                isFound = true;
                                if (transaction != null && transaction.getStatus().canRollback()) {
                                    transaction.rollback();
                                }
                                break;
                            }
                        }
                    }
                    if (!isFound) {
                        label2.setText("Bledny login !");
                        if (transaction != null && transaction.getStatus().canRollback()) {
                            transaction.rollback();
                        }
                    }
                } catch (Exception ex) {
                    if (transaction != null) {
                        if (transaction != null && transaction.getStatus().canRollback()) {
                            transaction.rollback();
                        }
                    }
                    ex.printStackTrace();
                } finally {
                    if (session != null && session.isOpen()) {
                        session.close();
                    }
                }
            }
        });

        // JButton - Cofnij
        buttonBack.setBackground(new Color(200, 230, 255));
        buttonBack.setVisible(true);
        buttonBack.setLayout(null);
        buttonBack.setBounds(0, 20, 100, 50);
        buttonBack.setText("Wstecz");
        buttonBack.setFocusable(false);
        buttonBack.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 150, 200), 2),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        buttonBack.setFont(new Font("Arial", Font.BOLD, 14));

        Login.add(textLogin);
        Password.add(textArea2);

        //panel5 to buttony zaloguj i pokaz haslo
        panel5.add(buttonBack);
        panel5.add(showPsw);
        panel5.add(jButton);

        //dodaje nowe panele
        jFrame.add(panel3);
        //to.add(panel4);
        jFrame.add(panel5);
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
