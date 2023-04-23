package example.InfoFrames;

import javax.swing.*;
import java.awt.*;

public class LoginInfoFrameSettings extends JFrame {
    public LoginInfoFrameSettings(JLabel label, JPanel panel, String text) {
        label.setText(text);
        label.setForeground(Color.black);
        label.setFont(new Font("MV Boli", Font.PLAIN, 32));
        label.setBackground(Color.blue); // to dziala ?
        label.setOpaque(false);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10),
                BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(100, 150, 200)),
                        BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(200, 230, 255))
                )
        ));
        panel.setBounds(60, 10, 400, 80);
        panel.setLayout(new BorderLayout());
        panel.add(label, BorderLayout.CENTER);
    }
}

