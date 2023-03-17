package example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.util.WeakHashMap;

public class login extends JFrame implements ActionListener {
    static boolean change = false;
    JLabel label,label2;
    JButton[] button = new JButton[7];
    //JButton button,button2,button3,button4,button5,button6;
    JPanel panel,panel2,panel3,panel4,panel5;
    TextField textField;

    //static String Label="Label";
    public login(){


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
        this.getContentPane().setBackground(Color.white);
        this.add(panel);
        this.add(panel2);

        //Button1 - "Zaloguj"
        button[0] = new JButton();
        button[0].addActionListener(this);
        Button1Settings button1Settings = new Button1Settings(button[0],panel,20,20,130,100);
        button[0].setText("Zaloguj");

        //Button2 - "Edytuj trening"
        button[1] = new JButton();
        button[1].addActionListener(this);
        Button2Settings button2Settings = new Button2Settings(button[1],panel);
        button[1].setText("Zarejestruj");

        //Button3 - "Wyjdz"
        button[2] = new JButton();
        button[2].addActionListener(this);
        Button3Settings button3Settings = new Button3Settings(button[2],panel);

    }

    public void setLabel(){
        label.setIcon(null);
        System.out.println("oj");
    }

    @Override
    public void actionPerformed(ActionEvent e) {



        if (e.getSource() == button[0]) {

            //Wylaczam stare panely
            panel.setVisible(false);
            panel2.setVisible(false);
            //

            //label
            label2 = new JLabel();

            //panel
            panel3 = new JPanel();
            panel3.setBackground(Color.white);

            WpisywanieTekstu wpisywanieTekstu = new WpisywanieTekstu(label2,panel3);
            this.add(panel3);


            //panel4
            panel4 = new JPanel();
            panel4.setBackground(Color.white);
            panel4.setBounds(45,110,450,100);
            panel4.setVisible(true);
            //panel4.setBackground(Color.white);
            this.add(panel4);

            //panel5
            panel5 = new JPanel();
            panel5.setBackground(Color.white);
            panel5.setBounds(80,210,355,50);
            panel5.setVisible(true);
            panel5.setLayout(null);
            //panel5.setBackground(Color.white);
            //panel.setBackground(Color.black);
            this.add(panel5);

            JTextArea textArea = new JTextArea();
            textArea.setText("login");
            textArea.setPreferredSize(new Dimension(210, 40)); // Ta komenda ustala wielkosc okna
            //textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);

            Border border = BorderFactory.createLineBorder(Color.BLACK); // ustaw styl obramowania
            // Ustaw rozmiar dla JTextField
            //panel4.setLayout(new GridBagLayout());
            //textArea.setBackground(Color.gray);
            textArea.setBorder(border);
            textArea.setForeground(new Color(0x000000));
            textArea.setFont(new Font("Consolas", Font.PLAIN,35));
            textArea.setBounds(0, 0, 340, 60);

            panel4.add(textArea);

            //

            JTextArea textArea2 = new JTextArea();
            textArea2.setBorder(border);
            textArea2.setText("haslo");
            textArea2.setPreferredSize(new Dimension(210, 40)); // Ta komenda ustala wielkosc okna
            //textArea.setLineWrap(true);
            textArea2.setWrapStyleWord(true);

            // Ustaw rozmiar dla JTextField
            //panel4.setLayout(new GridBagLayout());
            textArea2.setForeground(new Color(0x000000));
            textArea2.setFont(new Font("Consolas", Font.PLAIN,35));
            textArea2.setBounds(0, 0, 340, 60);

            panel4.add(textArea2);
            //

            JButton jButton = new JButton();
            jButton.setBackground(Color.white);
            jButton.setVisible(true);
            jButton.setLayout(null);
            jButton.setBounds(140,0,100,50);
            jButton.addActionListener(this);
            jButton.setText("Zapisz");
            jButton.setFocusable(false);
            jButton.addActionListener(this);
            jButton.setBorder(BorderFactory.createEtchedBorder());
            panel5.add(jButton);

            jButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //Ustawienia hibernate
                SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

                Session session = sessionFactory.getCurrentSession();
                session.beginTransaction();

                String text = textArea.getText();
                String text2 = textArea2.getText();
                System.out.println(text);
                List<User> users = session.createQuery("from User", User.class).getResultList();

                System.out.println("Użytkownicy w bazie danych:");
                for (User user : users) {
                    if(user.getUsername().equals(text)) {
                        System.out.println(user);
                        if(user.getPassword().equals(text2)) {
                            label2.setText("Zalogowano !");
                            panel3.setVisible(false);
                            panel4.setVisible(false);
                            panel5.setVisible(false);
                            panel.setVisible(true);
                            panel2.setVisible(true);

                            button[0].setText("Nowy trening");
                            button[1].setText("Edytuj trening");
                            button[2].setText("Wpisz mi 3 z PB");
                            break;
                        }
                    }
                }

                session.getTransaction().commit();
            }
        });

        }
        if (e.getSource() == button[2]) {
            if (change == false) {
                System.exit(0);
            }
        }
    }

}
