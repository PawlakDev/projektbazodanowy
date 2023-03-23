package example;

import javax.swing.*;
import java.awt.*;

public class textArea extends JTextArea{

    public textArea(String text) {
        JTextArea area = new JTextArea(text);
        area.setPreferredSize(new Dimension(210, 40)); // Ta komenda ustala wielkosc okna
        area.setWrapStyleWord(true);
        area.setForeground(new Color(0, 0, 0, 128));
       // area.setFont(new Font("MV Boli", 0, 32));
        area.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
}
