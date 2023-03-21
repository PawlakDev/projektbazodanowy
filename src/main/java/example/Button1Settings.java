package example;

import javax.swing.*;
import java.awt.*;

//docelowo usunąc to setEnbled
public class Button1Settings extends JFrame {
    public Button1Settings(JButton button, JPanel panel, int x, int y, int width, int height, boolean setEnabled, String text) {

        button.setBounds(x,y,width,height);
        button.setVisible(true);
        button.setFocusable(false);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.CENTER);
        button.setBorder(BorderFactory.createEtchedBorder());
        button.setEnabled(setEnabled);
        panel.add(button, BorderLayout.NORTH);
        button.setText(text);
    }
}
