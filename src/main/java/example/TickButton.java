package example;

import javax.swing.*;
import java.awt.*;

//Button potrzebny do klikniecia i odklikniecia na false dla boola poki co wersja bardzo robocza
public class TickButton {
    public TickButton(JButton button, JPanel panel, int x, int y, int width, int height) {
        JFrame frame = new JFrame("Tick Button Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JToggleButton tickButton = new JToggleButton();
        tickButton.setPreferredSize(new Dimension(20, 20));
        tickButton.addActionListener(e -> {
            if (tickButton.isSelected()) {
                tickButton.setBackground(Color.BLACK);
            } else {
                tickButton.setBackground(null);
            }
        });
        panel.add(tickButton);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}