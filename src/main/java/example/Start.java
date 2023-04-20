package example;

import example.buttons.Button1Settings;
import example.buttons.Button2Settings;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Start extends JFrame implements ActionListener {
    static boolean change = false;
    JLabel label;
    JButton[] button = new JButton[7];
    JFrame jFrame;

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
    JPanel ButtonPanel, BackgroundImagePanel;

    //Ustawienia hibernate
    SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

    public Start() {

        //Tworzenie panelu
        ButtonPanel = new JPanel();
        //Ustawianie jego wielkosci
        ButtonPanel.setBounds(0, 0, 180, 400);
        ButtonPanel.setLayout(null);
        //Ustawienie koloru tla
        ButtonPanel.setBackground(Color.white);

        //Tworzenie panelu dla obrazka
        BackgroundImagePanel = new JPanel();
        //Ustawienie wielkości pamietajac o wielkosciach pierwszego panelu
        BackgroundImagePanel.setBounds(160, -25, 370, 400);

        //Utwórz obiekt Image z obrazem
        Image image = new ImageIcon("src\\main\\java\\example\\obrazek.png").getImage();

        // Ustaw właściwość SCALE_SMOOTH dla obrazu
        image = image.getScaledInstance(500, 400, Image.SCALE_SMOOTH);

        // Utwórz obiekt ImageIcon z nowym obrazem
        ImageIcon imageIcon = new ImageIcon(image);

        // Utwórz obiekt JLabel z ikoną obrazu
        JLabel label = new JLabel(imageIcon);

        label.setBounds(0, 0, 400, 300);
        BackgroundImagePanel.add(label);

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

        //Button1 - "Zaloguj"
        button[0] = new JButton();
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
            Login login = new Login(ButtonPanel, BackgroundImagePanel, jFrame, jButton, ButtonBack);
            jButton.addActionListener(this);
            ButtonBack.addActionListener(this);

            jButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Session session = null;
                    Transaction transaction = null;
                    try {
                        session = sessionFactory.getCurrentSession();
                        transaction = session.beginTransaction();
                        boolean isFound = false;
                        String text = login.getTextLogin();
                        String text2 = new String(login.getTextArea2());
                        System.out.println(text);
                        List<User> users = session.createQuery("from User", User.class).getResultList();
                        for (User user : users) {
                            if (user.getUsername().equals(text)) {
                                if (user.getPassword().equals(text2)) {
                                    session.getTransaction().commit();


                                    //fajnie aby wyswielalo zalogowano przez 2 sekundy
                                    login.getPanel3().setEnabled(false);
                                    login.getPanel3().setVisible(false);
                                    login.getFrameBackground().setEnabled(false);
                                    login.getFrameBackground().setVisible(false);
                                    login.getPanel5().setEnabled(false);
                                    login.getPanel5().setVisible(false);

                                    BackgroundImagePanel.setEnabled(true);
                                    BackgroundImagePanel.setVisible(true);

                                    login.getLogin().setVisible(false);
                                    login.getLogin().setEnabled(false);
                                    login.getPassword().setVisible(false);
                                    login.getPassword().setEnabled(false);

                                    ButtonPanel.removeAll();
                                    ButtonPanel.setEnabled(true);
                                    ButtonPanel.setVisible(true);

                                    //Button3 - "Nowy trening"
                                    button[3] = new JButton();
                                    button[3].addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            // kod, który ma zostać wykonany po kliknięciu przycisku "Nowy trening"
                                            NewTraining newTraining = new NewTraining(ButtonPanel,BackgroundImagePanel, jFrame);
                                        }
                                    });
                                    Button1Settings button1Settings = new Button1Settings(button[3], ButtonPanel, 20, 20, 130, 100, true, "Nowy trening");

                                    //Button4 - "Edytuj trening"
                                    button[4] = new JButton();
                                    button[4].addActionListener(this);
                                    Button2Settings button2Settings = new Button2Settings(button[4], ButtonPanel, 20, 130, 130, 100, "Edytuj trening");

                                    //Button5 - "Wyswietl treningi"
                                    button[5] = new JButton();
                                    button[5].addActionListener(this);
                                    Button2Settings button3Settings = new Button2Settings(button[5], ButtonPanel, 20, 240, 130, 100, "Wyswietl treningi");

                                    button[6] = new JButton();
                                    button[6].addActionListener(this);
                                    Button2Settings button6Settings = new Button2Settings(button[6], ButtonPanel, 120, 240, 130, 100, "Witaj *twojnick*");

                                    jFrame.remove(BackgroundImagePanel);
                                    jFrame.remove(ButtonPanel);


                                    // To trzeba zrobic na samym poczatku aby pracowac od razu na warstwach
                                    JLayeredPane jLayeredPane = new JLayeredPane();
                                    jLayeredPane.setBounds(0, 0, 550, 400);
                                    jLayeredPane.add(BackgroundImagePanel, JLayeredPane.DEFAULT_LAYER); // ta "domyslna" co ma byc na dnie
                                    jLayeredPane.add(ButtonPanel, JLayeredPane.PALETTE_LAYER); // te ktore maja byc nad
                                    jFrame.add(jLayeredPane);
                                    //

                                    //session.getTransaction().commit();
                                    break;
                                } else {
                                    login.getLabel2().setText("Bledne haslo !");
                                    isFound = true;
                                    if (transaction != null && transaction.getStatus().canRollback()) {
                                        transaction.rollback();
                                    }
                                    break;
                                }
                            }
                        }
                        if (!isFound) {
                            login.getLabel2().setText("Bledny login !");
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
            JButton next = new JButton(); //panele do pozmieniania bo sa crazy
            JButton ButtonBack = new JButton();

            //CardLayout cardLayout = new CardLayout(); //Layout pozwala zmieniac framy na tym samym oknie

            MyFrameRejestracja signup = new MyFrameRejestracja(ButtonPanel, BackgroundImagePanel, next, ButtonBack);

            //ustawiam next button
            next.addActionListener(this);
            next.setText("dalej");
            login.getPanel5().add(next);

            login.getLabel2().setText("Zarejestruj sie");

            next.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    //te same co do logowania zmienne
                    String text = signup.getTextLogin();
                    String text2 = signup.getPassword(); // not safe but whatever for now

                    System.out.println(text);
                    System.out.println(text2);
                    if(text.isEmpty() || text.equals("login") || text2.isEmpty() || text2.equals("haslo"))
                        signup.getLabelTytul().setText("Uzupelnij haslo lub login!"); //settera by trzeba bylo zobic
                    else {
                        Session session = sessionFactory.getCurrentSession();
                        session.beginTransaction();

                        //addUser(sessionFactory,text, text2, "abc@e", true); //trzeba odzielne funkcje pododawać w klasie User
                        //zamiana framow 2/10
                        signup.setVisible(false);
                        MyFrameRejestracja frameRejestracja = new MyFrameRejestracja (ButtonPanel, BackgroundImagePanel, next, ButtonBack);
                        switchFrames(signup, frameRejestracja);
                    }
                }
            });
        }

        //wyjscie
        if (e.getSource() == button[2]) {
            if (change == false) {
                System.exit(0);
            }
        }
    }


    //metoda do zmiany framow
    public void switchFrames(JFrame oldFrame, JFrame newFrame) {
        oldFrame.setVisible(false);
        newFrame.setVisible(true);
    }
}