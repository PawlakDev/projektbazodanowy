package example.app.login;

import example.app.InfoFrames.WelcomeMsgSettings;
import example.app.buttons.Button1Settings;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class addTrainingDescriptions {

    JLabel WpisTekstLabel;
    JPanel WpisTekstPanel,ButtonPanel4;
    JTextArea jTextArea;

    public addTrainingDescriptions(JPanel ButtonPanel4){
        this.ButtonPanel4 = ButtonPanel4;

    }

    void zrob(){
        CreatingNewTraining creatingNewTraining = new CreatingNewTraining();

        ButtonPanel4.setVisible(false);
        ButtonPanel4.setEnabled(false);

        WpisTekstLabel = new JLabel();
        WpisTekstPanel = new JPanel();
        WpisTekstPanel.setOpaque(false);
        WpisTekstPanel.setBounds(50,120,300,190);

        creatingNewTraining.getjLayeredPane().add(WpisTekstPanel, JLayeredPane.PALETTE_LAYER); //

        WelcomeMsgSettings welcomeMsgSettings1 = new WelcomeMsgSettings(creatingNewTraining.getWellcomeMsgLabel(), creatingNewTraining.getWellcomeMsgPanel(), "Wpisz Opis", 120, 310 );

        jTextArea = new JTextArea();
        jTextArea.setPreferredSize(new Dimension(300, 180));
        jTextArea.setFont(new Font("MV Boli", 0, 32));
        jTextArea.setForeground(new Color(0, 0, 0, 128));
        jTextArea.setBorder(new LineBorder(Color.BLACK));

        WpisTekstPanel.add(jTextArea);

        JPanel ButtonPanelApply = new JPanel();

        JButton buttonApply = new JButton();
        Button1Settings buttonApplySettings = new Button1Settings(buttonApply, ButtonPanelApply, 370, 230, 130, 70, true, "Zatwierdz");


        buttonApply.setVisible(true);
        buttonApply.setEnabled(true);

        ButtonPanelApply.add(buttonApply);
        creatingNewTraining.getjLayeredPane().add(buttonApply, JLayeredPane.PALETTE_LAYER); //
    }
}
