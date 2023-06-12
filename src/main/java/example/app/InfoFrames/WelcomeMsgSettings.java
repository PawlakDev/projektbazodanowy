package example.app.InfoFrames;

import javax.swing.*;
import java.awt.*;

public class WelcomeMsgSettings extends JFrame {
    public WelcomeMsgSettings(JLabel label, JPanel panel, String text,int x ,int width) {


        label.setText(text);
        label.setForeground(Color.black);
        label.setFont(new Font("MV Boli", Font.PLAIN, 32));
        label.setBackground(new Color(197, 219, 233)); // to dziala ? - tak jeżeli setOpaque jest na true
        label.setOpaque(true);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0, 0, 0, 0),
                BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(100, 150, 200)),
                        BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(200, 230, 255))
                )
        ));
        panel.setBounds(x, 20, width, 60);

        // Ustaw wymiary etykiety na takie same jak panel
        label.setPreferredSize(new Dimension(panel.getWidth(), panel.getHeight()));
        label.setSize(panel.getSize());

        panel.setLayout(new BorderLayout());
        panel.add(label, BorderLayout.CENTER);
    }
}

