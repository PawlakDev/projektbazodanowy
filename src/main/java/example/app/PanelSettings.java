package example.app;

import javax.swing.*;

public class PanelSettings extends JPanel {
    public PanelSettings(JPanel panel, int x, int y, int width, int height) {
        panel.setBounds(x, y, width, height);
        panel.setVisible(true);
    }
}
