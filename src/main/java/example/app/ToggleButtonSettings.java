package example.app;

import javax.swing.*;
import java.awt.*;

//Button potrzebny do klikniecia i odklikniecia
public class ToggleButtonSettings {
    public ToggleButtonSettings(JToggleButton tickButton, int x, int y, int width, int height) {
        tickButton.setPreferredSize(new Dimension(20, 20));
        tickButton.setBounds(x, y, width, height);
        tickButton.addActionListener(e -> {
            if (tickButton.isSelected()) {
                tickButton.setBackground(Color.BLACK);
            } else {
                tickButton.setBackground(null);
            }
        });
    }
}