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
    //JButton button,button2,button3,button4,button5,button6;
    JPanel panel,panel2,panel3,panel4,panel5; //panel 2 to zdjecie, panel to buttony
    TextField textField;

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

        // Utwórz obiekt Image z obrazem
        Image image = new ImageIcon("src\\main\\java\\example\\obrazek.png").getImage();

        // Ustaw właściwość SCALE_SMOOTH dla obrazu
        image = image.getScaledInstance(500, 400, Image.SCALE_SMOOTH);

        // Utwórz obiekt ImageIcon z nowym obrazem
        ImageIcon imageIcon = new ImageIcon(image);

        // Utwórz obiekt JLabel z ikoną obrazu
        JLabel label = new JLabel(imageIcon);


        label.setBounds(0, 0, 400, 300);
        //label.setIcon(icon);
        panel2.add(label);

        //JFrame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(550,400);
        this.setVisible(true);
        this.setResizable(false);
        this.setLayout(null);
        this.setTitle("Dzienniczek treningowy PZTW");
        //Ustawienie koloru !!
        this.getContentPane().setBackground(Color.white);
        //
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

    public void setLabel(){
        label.setIcon(null);
        System.out.println("oj");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //login
        if (e.getSource() == button[0]) {

            JButton jButton = new JButton();
            JButton ButtonBack = new JButton();
            Login frame2 = new Login(panel, panel2, this, jButton, ButtonBack);
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
                        String text = frame2.getTextLogin();
                        String text2 = new String(frame2.getTextArea2());
                        System.out.println(text);
                        List<User> users = session.createQuery("from User", User.class).getResultList();
                        for (User user : users) {
                            if (user.getUsername().equals(text)) {
                                if (user.getPassword().equals(text2)) {
                                    session.getTransaction().commit();

                                    //fajnie aby wyswielalo zalogowano przez 2 sekundy
                                    frame2.getPanel3().setEnabled(false);
                                    frame2.getFrameBackground().setEnabled(false);
                                    frame2.getPanel5().setEnabled(false);

                                    panel.setEnabled(true);
                                    panel2.setEnabled(true);

                                    //to są te same buttony ze zmieniona nazwa
                                    button[0].setText("Nowy trening");
                                    button[1].setText("Edytuj trening");
                                    button[2].setText("Wpisz mi 3 z PB");

                                    //session.getTransaction().commit();

                                    //isFound = true;

                                    break;
                                } else {
                                    frame2.getLabel2().setText("Bledne haslo !");
                                    isFound = true;
                                    if (transaction != null && transaction.getStatus().canRollback()) {
                                        transaction.rollback();
                                    }
                                    break;
                                }
                            }
                        }
                        if (!isFound) {
                            frame2.getLabel2().setText("Bledny login !");
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
                    frame2.getPanel5().setVisible(false);
                    frame2.getGraphicFrame().setVisible(false);
                    frame2.getPanel3().setVisible(false);
                    panel.setVisible(true);
                    panel2.setVisible(true);
                }
            });
        }

        //signup - rejestracja
        //panele ogarnac
        else if (e.getSource() == button[1]) {

            JButton next = new JButton(); //panele do pozmieniania
            JButton ButtonBack = new JButton();

            //to mialo byc wykorzystane do nowego framea ale tego samego kna ale nie dziala
            //CardLayout cardLayout = new CardLayout(); //Layout pozwala zmieniac framy na tym samym oknie

            Login frame2 = new Login(panel, panel2, this, next, ButtonBack);

            //ustawiam next button
            next.addActionListener(this);
            next.setText("dalej");
            frame2.getPanel5().add(next);

            frame2.getLabel2().setText("Zarejestruj sie");

            next.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    //te same o do logowania

                    String text = frame2.getTextLogin();
                    String text2 = frame2.getTextArea2();

                    System.out.println(text);
                    System.out.println(text2);
                    if(text.isEmpty() || text2.isEmpty() || text2.equals("haslo"))
                        frame2.getLabel2().setText("Uzupelnij haslo lub login!");
                    else {
                        Session session = sessionFactory.getCurrentSession();
                        session.beginTransaction();

                        //addUser(sessionFactory,text, text2, "abc@e", true); //trzeba odzielne funkcje pododawać
                        frame2.setVisible(false);
                        MyFrameRejestracja frameRejestracja = new MyFrameRejestracja(next); //dodac moze panele?

                        switchFrames(frame2, frameRejestracja);
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

    //metoda do zmiany frammow
    public void switchFrames(JFrame oldFrame, JFrame newFrame) {
        oldFrame.setVisible(false);
        newFrame.setVisible(true);
    }
}