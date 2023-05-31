package example;

import example.InfoFrames.LoginInfoFrameSettings;
import org.hibernate.SessionFactory;

import javax.swing.*;

import java.awt.*;

import static example.Start.getBackgroundImagePanel;

public class ShowTrainingsAll extends JFrame {

    JLabel headlineLabel;
    JPanel headlinePanel;
    JLayeredPane LayeredPane;

    JTextArea textArea;
    JScrollPane scrollPane;
    public ShowTrainingsAll(SessionFactory sessionFactory, JFrame to, JPanel oldButtonPanel) {
        System.out.println("show all trainings view");

        // wylaczam stare widoki
        oldButtonPanel.setVisible(false);
        oldButtonPanel.setEnabled(false);
        getBackgroundImagePanel().setVisible(false);

        // ustawiam headline
        headlineLabel = new JLabel();
        headlinePanel = new JPanel();
        headlinePanel.setBackground(Color.white);
        LoginInfoFrameSettings loginInfoFrameSettings = new LoginInfoFrameSettings(headlineLabel,headlinePanel, "Twoje treningi");

        to.add(headlinePanel);

        // ustawiam pole tekstoowe
        textArea = new JTextArea();
        textArea.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus non dapibus nisl, eu dignissim orci. \nVivamus vulputate mauris diam, nec vulputate felis faucibus sed. Curabitur pellentesque sed nibh et ultrices. Nunc enim neque, malesuada et orci ac, feugiat scelerisque diam. \nInteger ut venenatis ipsum, ut vulputate enim. Maecenas cursus iaculis laoreet. Etiam ut mattis leo. Ut condimentum vulputate suscipit. \nVestibulum in urna vulputate, sollicitudin ligula sit amet, venenatis dui.");

        // Tworzenie panelu przewijania
        scrollPane = new JScrollPane(textArea);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(400, 300)); // Możesz dostosować szerokość i wysokość według potrzeb
        scrollPane.setBounds(70,100,400,230);
        scrollPane.setVisible(true);

        to.add(scrollPane);

    }
}
