package example;

import javax.swing.*;
import java.awt.*;

public class WpisywanieTekstu extends JFrame {
    public WpisywanieTekstu(JLabel label, JPanel panel) {
        label.setText("Zaloguj sie");
        label.setBounds(190,0,300,80);
        label.setFont(new Font("MV Boli",Font.PLAIN,32));
        label.setBackground(Color.white);

        panel.setBounds(0,0,400,80);
        panel.setLayout(null);
        //panel.setBackground(Color.black);

        panel.add(label);
    }
}

