package example.buttons;

import javax.swing.*;
import java.awt.*;

public class Button2Settings extends JFrame {
    public Button2Settings(JButton button, JPanel panel, int x, int y, int width, int height, String text) {
        button.setVisible(true);
        button.setBounds(x,y,width,height);
        button.setText(text);
        button.setFocusable(false);
        button.setEnabled(true);
        button.setBorder(BorderFactory.createEtchedBorder());
        panel.add(button , BorderLayout.NORTH);
    }
}
