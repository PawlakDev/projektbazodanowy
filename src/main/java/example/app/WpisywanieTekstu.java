package example.app;

import javax.swing.*;
import java.awt.*;

public class WpisywanieTekstu {
    public WpisywanieTekstu(JLabel label, JPanel panel) {
        label.setText("Wpisz opis");
        label.setBounds(100, 0, 400, 80);
        label.setFont(new Font("MV Boli", Font.PLAIN, 32));
        label.setBackground(Color.PINK);

        panel.setBounds(0, 0, 400, 80);
        panel.setLayout(null);

        panel.add(label);
    }
}
