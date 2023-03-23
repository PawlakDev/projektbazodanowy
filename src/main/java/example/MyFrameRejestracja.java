package example;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import static example.MyFrame2.createTextArea;

public class MyFrameRejestracja extends JFrame {
    JLabel labelTytul;
    JPanel panelTytul, panel, panel2;

    JTextArea emailText;
    MyFrameRejestracja(JButton jButton) {

        setLayout(new BorderLayout());

        //ustawial label
        labelTytul = new JLabel();

        //i panel do niego
        panelTytul = new JPanel();
        panelTytul.setBackground(Color.white);
        WpisywanieTekstu wpisywanieTekstu = new WpisywanieTekstu(labelTytul,panelTytul, "Uzupelnij dane");


        //Tworzenie panelu to jest skopiowany z guzikow z frame2
        panel = new JPanel();
        //Ustawianie jego wielkosci
        panel.setBounds(4,100,500,400); //to jest tn bialy tlo
        //panel.setLayout(null);
        //Ustawienie koloru tla
        panel.setBackground(Color.white);

        //textArea - login
        emailText = createTextArea("email");

        //Tworzenie drugiego panelu
        panel2 = new JPanel();
        //Ustawienie wielkości pamietajac o wielkosciach pierwszego panelu
        panel2.setBounds(160,-25,370,400);

        panel.add(emailText);

        panel.setVisible(true);
        panel2.setVisible(true);
        panelTytul.setVisible(true);

        this.add(panelTytul);
        this.add(panel);
        this.add(panel2);

        //na sztywno poki c ale trzeba zmienna zrobic
        setSize(550, 400);

        //aby zamykanie dzialalo
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //robie visible frama
        setVisible(true);
    }
}
