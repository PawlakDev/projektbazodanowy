package example;

import javax.swing.*;
import java.awt.*;

public class MyFrameRejestracja extends JFrame {
    JLabel labelTytul;
    JPanel panelTytul;
    MyFrameRejestracja(JFrame to, JButton jButton) {

        //ustawial label
        labelTytul = new JLabel();

        //i panel do niego
        panelTytul = new JPanel();
        panelTytul.setBackground(Color.white);
        WpisywanieTekstu wpisywanieTekstu = new WpisywanieTekstu(labelTytul,panelTytul, "Uzupelnij dane");
        panelTytul.setVisible(true);
        System.out.println("guzik dalej wchodzi");
    }
}
