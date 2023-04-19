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
    JPanel panel,panel2; //panel 2 to zdjecie, panel to buttony

    //Ustawienia hibernate
    SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

    public Start(){

        //Tworzenie panelu
        panel = new JPanel();
        //Ustawianie jego wielkosci
        panel.setBounds(0,0,180,400);
        panel.setLayout(null);
        //Ustawienie koloru tla
        panel.setBackground(Color.white);

        //Tworzenie drugiego panelu
        panel2 = new JPanel();
        //Ustawienie wielkości pamietajac o wielkosciach pierwszego panelu
        panel2.setBounds(160,-25,370,400);

        //Utwórz obiekt Image z obrazem
        Image image = new ImageIcon("src\\main\\java\\example\\obrazek.png").getImage();

        // Ustaw właściwość SCALE_SMOOTH dla obrazu
        image = image.getScaledInstance(500, 400, Image.SCALE_SMOOTH);

        // Utwórz obiekt ImageIcon z nowym obrazem
        ImageIcon imageIcon = new ImageIcon(image);

        // Utwórz obiekt JLabel z ikoną obrazu
        JLabel label = new JLabel(imageIcon);

        label.setBounds(0, 0, 400, 300);
        panel2.add(label);

        //JFrame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(550,400);
        this.setVisible(true);
        this.setResizable(false);
        this.setLayout(null);
        this.setTitle("Dzienniczek treningowy PZTW");
        //Ustawienie koloru
        this.getContentPane().setBackground(Color.white);

        //dodaje panele do obecnego frema
        this.add(panel);
        this.add(panel2);

        //Button1 - "Zaloguj"
        button[0] = new JButton();
        button[0].addActionListener(this);
        Button1Settings button1Settings = new Button1Settings(button[0],panel,20,20,130,100, true, "Zaloguj");

        //Button2 - "zarejestruj"
        button[1] = new JButton();
        button[1].addActionListener(this);
        Button2Settings button2Settings = new Button2Settings(button[1],panel, 20,130,130,100, "Zarejestruj");

        //Button3 - "Wyjdz"
        button[2] = new JButton();
        button[2].addActionListener(this);
        Button2Settings button3Settings = new Button2Settings(button[2],panel, 20,240,130,100, "Wyjdz");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //ten kod wrzocic do klasy login
        if (e.getSource() == button[0]) {

            JButton jButton = new JButton();
            JButton ButtonBack = new JButton();
            Login login = new Login(panel, panel2, this, jButton, ButtonBack);
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

                                    //Wylaczanie starych przyciskow
                                    for(int i=0; i<3;i++){
                                        button[i].setVisible(false);
                                        button[i].setEnabled(false);
                                    }

                                    panel.setEnabled(true);
                                    panel.setVisible(true);
                                    panel2.setEnabled(true);
                                    panel2.setVisible(true);

                                    login.getLogin().setVisible(false);
                                    login.getLogin().setEnabled(false);
                                    login.getPassword().setVisible(false);
                                    login.getPassword().setEnabled(false);


                                    //Button3 - "Nowy trening"
                                    button[3] = new JButton();
                                    button[3].addActionListener(this);
                                    Button1Settings button1Settings = new Button1Settings(button[3],panel,20,20,130,100, true, "Nowy trening");

                                    //Button4 - "Edytuj trening"
                                    button[4] = new JButton();
                                    button[4].addActionListener(this);
                                    Button2Settings button2Settings = new Button2Settings(button[4],panel, 20,130,130,100, "Edytuj trening");

                                    //Button5 - "Wyswietl treningi"
                                    button[5] = new JButton();
                                    button[5].addActionListener(this);
                                    Button2Settings button3Settings = new Button2Settings(button[5],panel, 20,240,130,100, "Wyswietl treningi");

                                    button[3].addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            // Kod obsługi kliknięcia przycisku "Nowy trening"
                                            System.out.println("Siema");
                                        }
                                    });
                                    //NewTraining newTraining = new NewTraining(panel, panel2, this, jButton, ButtonBack);

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
                    login.getPanel5().setVisible(false);
                    login.getGraphicFrame().setVisible(false);
                    login.getPanel3().setVisible(false);
                    panel.setVisible(true);
                    panel2.setVisible(true);
                }
            });
        }

        //signup - rejestraca
        else if (e.getSource() == button[1]) {

            JButton next = new JButton(); //panele do pozmieniania bo sa crazy
            JButton ButtonBack = new JButton();

            //to mialo byc wykorzystane do nowego framea ale tego samego kna ale nie dziala
            //CardLayout cardLayout = new CardLayout(); //Layout pozwala zmieniac framy na tym samym oknie

            Login login = new Login(panel, panel2, this, next, ButtonBack);

            //ustawiam next button
            next.addActionListener(this);
            next.setText("dalej");
            login.getPanel5().add(next);

            login.getLabel2().setText("Zarejestruj sie");

            next.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    //te same co do logowania zmienne
                    String text = login.getTextLogin();
                    String text2 = login.getTextArea2();

                    System.out.println(text);
                    System.out.println(text2);
                    if(text.isEmpty() || text.equals("login") || text2.isEmpty() || text2.equals("haslo"))
                        login.getLabel2().setText("Uzupelnij haslo lub login!");
                    else {
                        Session session = sessionFactory.getCurrentSession();
                        session.beginTransaction();

                        //addUser(sessionFactory,text, text2, "abc@e", true); //trzeba odzielne funkcje pododawać w klasie User
                        //zamiana framow 2/10
                        login.setVisible(false);
                        MyFrameRejestracja frameRejestracja = new MyFrameRejestracja(next);
                        switchFrames(login, frameRejestracja);
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