package example;

import javax.swing.*;
import java.awt.*;

public class WpisywanieTekstu extends JFrame {
    public WpisywanieTekstu(JLabel label, JPanel panel) {
        label.setText("Zaloguj sie");
        label.setFont(new Font("MV Boli", Font.PLAIN, 32));
        label.setBackground(Color.white);
        label.setOpaque(false);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10),
                BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(4, 4, 4, 4, Color.BLACK),
                        BorderFactory.createMatteBorder(1, 1, 1, 1, Color.WHITE)
                )
        ));

        panel.setBounds(60, 15, 400, 80);
        panel.setLayout(new BorderLayout());
        panel.add(label, BorderLayout.CENTER);
    }
}

