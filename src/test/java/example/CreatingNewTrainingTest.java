package example;

import example.app.login.CreatingNewTraining;
import example.app.buttons.Button1Settings;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Thread.sleep;

class CreatingNewTrainingTest {
    private SessionFactory session;
    private JPanel ButtonPanel3,BackgroundImagePanel;
    private JFrame frame ;
    private JLayeredPane jLayeredPane;
    JButton[] button = new JButton[6];
    @Test
    void cos() throws InterruptedException {
        ////////////////////////////////////////////////////////////////////////////////////
        //Uzupełnienie wymaganych wartości :)
        ////////////////////////////////////////////////////////////////////////////////////
        frame = new JFrame();

        //JFrame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550, 400);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setTitle("Dzienniczek treningowy PZTW");
        //Ustawienie koloru
        frame.getContentPane().setBackground(Color.white);
        //

        //JLayeredPane
        jLayeredPane = new JLayeredPane();
        //

        //JPanel
        ButtonPanel3 = new JPanel();
        ButtonPanel3.setBounds(0, 0, 180, 400);
        ButtonPanel3.setLayout(null);
        //Ustawienie koloru tla
        ButtonPanel3.setBackground(Color.white);

        button[3] = new JButton();
        Button1Settings button3Settings = new Button1Settings(button[3], ButtonPanel3, 20, 20, 130, 100, true, "Aktualna Data");

        button[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreatingNewTraining creatingNewTraining = new CreatingNewTraining(session, ButtonPanel3, BackgroundImagePanel, frame, jLayeredPane);

            }
        });


        button[4] = new JButton();
        Button1Settings button4Settings = new Button1Settings(button[4], ButtonPanel3, 20, 130, 130, 100, true, "Wpisz recznie date");

        button[5] = new JButton();
        Button1Settings button5Settings = new Button1Settings(button[5], ButtonPanel3, 20, 240, 130, 100, true, "Cofnij");
        //

        //
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
        //

        jLayeredPane.setBounds(0, 0, 550, 400);
        jLayeredPane.add(BackgroundImagePanel, JLayeredPane.DEFAULT_LAYER);

        frame.add(jLayeredPane);
        frame.add(ButtonPanel3);
        ////////////////////////////////////////////////////////////////////////////////////

        CreatingNewTraining creatingNewTraining = new CreatingNewTraining(session,ButtonPanel3,BackgroundImagePanel,frame,jLayeredPane);
        creatingNewTraining.zrob();


        sleep(500000);
    }
}