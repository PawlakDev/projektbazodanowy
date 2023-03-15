package example;

import javax.swing.*;
import java.awt.*;

public class Button1Settings extends JFrame {
    public Button1Settings(JButton button, JPanel panel, int x, int y, int width, int height) {

        button.setBounds(x,y,width,height);
        button.setVisible(true);
        button.setFocusable(false);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.CENTER);
        button.setBorder(BorderFactory.createEtchedBorder());
        button.setEnabled(true);
        panel.add(button, BorderLayout.NORTH);
    }
}
