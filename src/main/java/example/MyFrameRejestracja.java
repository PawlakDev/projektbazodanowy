package example;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class MyFrameRejestracja extends JFrame {
    JLabel labelTytul;
    JPanel panelTytul, panel, panel2;
    MyFrameRejestracja(JButton jButton) {
        System.out.println("wchodzi rejestracja");

        setLayout(new BorderLayout());

        //ustawial label
        labelTytul = new JLabel();

        //i panel do niego
        panelTytul = new JPanel();
        panelTytul.setBackground(Color.white);
        WpisywanieTekstu wpisywanieTekstu = new WpisywanieTekstu(labelTytul,panelTytul, "Uzupelnij dane");
        panelTytul.setVisible(true);

        this.add(panelTytul);

        //Tworzenie panelu
        panel = new JPanel();
        //Ustawianie jego wielkosci
        panel.setBounds(4,5,180,400);
        panel.setLayout(null);
        //Ustawienie koloru tla
        panel.setBackground(Color.white);

        //Tworzenie drugiego panelu
        panel2 = new JPanel();
        //Ustawienie wielkości pamietajac o wielkosciach pierwszego panelu
        panel2.setBounds(160,-25,370,400);
        this.add(panel);
        panel.setVisible(true);
        panel2.setVisible(true);
        this.add(panel2);

        // Set the size of the frame
        setSize(550, 400);

        //Set the default close operation
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //robie visible frama
        setVisible(true);
    }
}
