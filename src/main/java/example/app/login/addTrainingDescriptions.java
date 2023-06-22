package example.app.login;

import example.app.InfoFrames.WelcomeMsgSettings;
import example.app.buttons.Button1Settings;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class addTrainingDescriptions {

    JLabel WpisTekstLabel;
    JPanel WpisTekstPanel, ButtonPanel4;
    JTextArea jTextArea;

    JLayeredPane jLayeredPane;
    JLabel wellcomeMsgLabel;
    JPanel wellcomeMsgPanel;

    public addTrainingDescriptions(JPanel ButtonPanel4, JLayeredPane jLayeredPane, JLabel wellcomeMsgLabel, JPanel wellcomeMsgPanel) {
        this.ButtonPanel4 = ButtonPanel4;
        this.jLayeredPane = jLayeredPane;
        this.wellcomeMsgLabel = wellcomeMsgLabel;
        this.wellcomeMsgPanel = wellcomeMsgPanel;
    }

    void zrob() {
        CreatingNewTraining creatingNewTraining = new CreatingNewTraining();

        ButtonPanel4.setVisible(false);
        ButtonPanel4.setEnabled(false);

        WpisTekstLabel = new JLabel();
        WpisTekstPanel = new JPanel();
        WpisTekstPanel.setOpaque(false);
        WpisTekstPanel.setBounds(50, 120, 300, 190);

        jLayeredPane.add(WpisTekstPanel, JLayeredPane.PALETTE_LAYER); //

        WelcomeMsgSettings welcomeMsgSettings1 = new WelcomeMsgSettings(wellcomeMsgLabel, wellcomeMsgPanel, "Wpisz Opis", 120, 310);

        jTextArea = new JTextArea();
        jTextArea.setPreferredSize(new Dimension(300, 180));
        jTextArea.setFont(new Font("MV Boli", 0, 32));
        jTextArea.setForeground(new Color(0, 0, 0, 128));
        jTextArea.setBorder(new LineBorder(Color.BLACK));

        WpisTekstPanel.add(jTextArea);

        JPanel ButtonPanelApply = new JPanel();

        JButton buttonBack = new JButton();

        JButton buttonApply = new JButton();
        buttonApply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String opis = jTextArea.getText();

                jTextArea.setText("");
                WelcomeMsgSettings welcomeMsgSettings2 = new WelcomeMsgSettings(wellcomeMsgLabel, wellcomeMsgPanel, "Wpisz ilosc km", 120, 310);

                buttonApply.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String km = jTextArea.getText();


                    }
                });
            }
        });

        Button1Settings buttonApplySettings = new Button1Settings(buttonApply, ButtonPanelApply, 370, 230, 130, 70, true, "Zatwierdz");
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonPanel4.setVisible(true);
                ButtonPanel4.setEnabled(true);

                WpisTekstPanel.setVisible(false);
                WpisTekstPanel.setEnabled(false);

                jTextArea.setEnabled(false);
                jTextArea.setVisible(false);

                buttonBack.setVisible(false);
                buttonBack.setEnabled(false);

                buttonApply.setVisible(false);
                buttonApply.setEnabled(false);

                WelcomeMsgSettings welcomeMsgSettings = new WelcomeMsgSettings(wellcomeMsgLabel, wellcomeMsgPanel, "Wybierz typ treningu", 70, 380);
            }
        });
        Button1Settings buttonBackSettings = new Button1Settings(buttonBack, ButtonPanelApply, 370, 150, 130, 70, true, "Cofnij");


        buttonApply.setVisible(true);
        buttonApply.setEnabled(true);

        ButtonPanelApply.add(buttonBack);
        ButtonPanelApply.add(buttonApply);
        jLayeredPane.add(buttonApply, JLayeredPane.PALETTE_LAYER); //
        jLayeredPane.add(buttonBack, JLayeredPane.PALETTE_LAYER); //
    }
}
