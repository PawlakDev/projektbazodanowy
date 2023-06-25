package example.app;

import example.app.buttons.Button1Settings;
import example.app.buttons.Button2Settings;
import example.app.login.Login;
import example.app.register.Rejestracja;
import org.hibernate.SessionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Start extends JFrame implements ActionListener {
    public static Object session; //?
    public static JButton jButton;
    public static JButton ButtonBack;
    static boolean change = false;
    public static  JFrame jFrame;
    static JLayeredPane jLayeredPane;
    /*
     *  Buttons:
     *  0 - Zaloguj
     *  1 - Zarejestruj
     *  2 - Wyjdz
     *  3 - Nowy trening
     *  4 - Edytuj trening
     *  5 - Wyswietl treningi
     *
     */
    public static JPanel ButtonPanel;
    static JPanel BackgroundImagePanel;
    JLabel label, welcomeMsgLabel;
    JButton[] button = new JButton[7];
    JPanel WelcomeMsgPanel;

    SessionFactory sessionFactory;

    public Start() {

    }

    public Start(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static JPanel getButtonPanel() {
        return ButtonPanel;
    }

    public static JPanel getBackgroundImagePanel() {
        return BackgroundImagePanel;
    }

    public static JLayeredPane getjLayeredPane() {
        return jLayeredPane;
    }

    public void zrob() {

        stworzButtony();

        stworzBackground();

        stworzJframe();

        stworzJlayeredpane();

        for (int i = 0; i < 3; i++) {
            button[i].setVisible(true);
            button[i].setEnabled(true);
            button[i].setRolloverEnabled(false);
        }
    }

    private void stworzJlayeredpane() {
        // JLayeredPane
        jLayeredPane = new JLayeredPane();
        jLayeredPane.setBounds(0, 0, 550, 400);
        jLayeredPane.add(BackgroundImagePanel, JLayeredPane.DEFAULT_LAYER); // ta "domyslna" co ma byc na dnie
        jLayeredPane.add(ButtonPanel, JLayeredPane.PALETTE_LAYER); // te ktore maja byc nad
        jFrame.add(jLayeredPane);
        //
    }

    private void stworzBackground() {
        //Tworzenie panelu dla obrazka
        BackgroundImagePanel = new JPanel();
        //Ustawienie wielkości pamietajac o wielkosciach pierwszego panelu
        BackgroundImagePanel.setBounds(160, -25, 370, 400);

        //Utwórz obiekt Image z obrazem
        Image image = new ImageIcon("src/main/java/example/obrazek.png").getImage();

        // Ustaw właściwość SCALE_SMOOTH dla obrazu
        image = image.getScaledInstance(500, 400, Image.SCALE_SMOOTH);

        // Utwórz obiekt ImageIcon z nowym obrazem
        ImageIcon imageIcon = new ImageIcon(image);

        // Utwórz obiekt JLabel z ikoną obrazu
        JLabel label = new JLabel(imageIcon);

        label.setBounds(0, 0, 400, 300);
        BackgroundImagePanel.add(label);
    }

    private void stworzJframe() {
        //JFrame
        jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(550, 400);
        jFrame.setVisible(true);
        jFrame.setResizable(false);
        jFrame.setLayout(null);
        jFrame.setTitle("Dzienniczek treningowy PZTW");
        //Ustawienie koloru
        jFrame.getContentPane().setBackground(Color.white);

        //dodaje panele do obecnego frema
        jFrame.add(ButtonPanel);
        jFrame.add(BackgroundImagePanel);
    }

    private void stworzButtony() {
        //Tworzenie panelu
        ButtonPanel = new JPanel();
        //Ustawianie jego wielkosci
        ButtonPanel.setBounds(0, 0, 180, 400);
        ButtonPanel.setLayout(null);
        //Ustawienie koloru tla
        ButtonPanel.setBackground(Color.white);

        //Button1 - "Zaloguj"
        button[0] = new JButton();
        button[0].setVisible(true);
        button[0].setEnabled(true);
        button[0].addActionListener(this);
        Button1Settings button1Settings = new Button1Settings(button[0], ButtonPanel, 20, 20, 130, 100, true, "Zaloguj");

        //Button2 - "zarejestruj"
        button[1] = new JButton();
        button[1].addActionListener(this);
        Button2Settings button2Settings = new Button2Settings(button[1], ButtonPanel, 20, 130, 130, 100, "Zarejestruj");

        //Button3 - "Wyjdz"
        button[2] = new JButton();
        button[2].addActionListener(this);
        Button2Settings button3Settings = new Button2Settings(button[2], ButtonPanel, 20, 240, 130, 100, "Wyjdz");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //ten kod wrzocic do klasy login
        if (e.getSource() == button[0]) {

            JButton jButton = new JButton();
            JButton ButtonBack = new JButton();
            Login login = new Login(jFrame, jButton, ButtonBack, button, sessionFactory, BackgroundImagePanel, ButtonPanel, WelcomeMsgPanel, welcomeMsgLabel);
            login.zrob();
            jButton.addActionListener(this);
            ButtonBack.addActionListener(this);


            ButtonBack.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == button[3]) {
                        System.out.println("ELO ELOOOO");
                    }

                    login.getPanel5().setVisible(false);
                    login.getGraphicFrame().setVisible(false);
                    login.getPanel3().setVisible(false);
                    ButtonPanel.setVisible(true);
                    BackgroundImagePanel.setVisible(true);
                }
            });
        }

        //signup - rejestraca
        else if (e.getSource() == button[1]) {
            //Otwieram frame rejstracja
            Rejestracja signup = new Rejestracja(sessionFactory, jFrame, ButtonPanel, BackgroundImagePanel);
        }

        //wyjscie
        if (e.getSource() == button[2]) {
            if (!change) {
                System.exit(0);
            }
        }
    }
}