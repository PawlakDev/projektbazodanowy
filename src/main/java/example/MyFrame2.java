package example;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.JToggleButton;
import java.awt.*;

public class MyFrame2 extends JFrame {
JLabel label2;
JPanel panel3, panel4, panel5;
JTextArea textArea, textArea2, textArea3, textArea4; //1 - login, 2 - haslo 3 - email, 4 - funkcja

    MyFrame2(JPanel panel, JPanel panel2, JFrame to, JButton jButton){

        //Wylaczam stare panely
            panel.setVisible(false);
            panel2.setVisible(false);

        //label
        label2 = new JLabel();

        //panel
        panel3 = new JPanel();
        panel3.setBackground(Color.white);

        WpisywanieTekstu wpisywanieTekstu = new WpisywanieTekstu(label2,panel3);
        to.add(panel3);

        //panel4
        panel4 = new JPanel();
        panel4.setBackground(Color.white);
        panel4.setBounds(45,110,450,100);
        panel4.setVisible(true);
        //panel4.setBackground(Color.white);
        to.add(panel4);

        //panel5
        panel5 = new JPanel();
        panel5.setBackground(Color.white);
        panel5.setBounds(80,210,355,50);
        panel5.setVisible(true);
        panel5.setLayout(null);
        //panel5.setBackground(Color.white);
        //panel.setBackground(Color.black);
        to.add(panel5);

        //textArea - login
        textArea = createTextArea("login");

        //Border border = BorderFactory.createLineBorder(Color.BLACK); // ustaw styl obramowania

        // Ustaw rozmiar dla JTextField
        panel4.setLayout(new GridBagLayout());

        panel4.add(textArea);

        //haslo
        textArea2 = createTextArea("haslo");
        panel4.add(textArea2);

        //email
        textArea3 = createTextArea("email"); //nie wiem czy nie edzie trzeba zmienic polozenia

        //TickButton czyZawodnik = TickButton("");
        //panel4.add(textArea3);

        //jButton = createButton("Zaloguj")
        jButton.setBackground(Color.white);
        jButton.setVisible(true);
        jButton.setLayout(null);
        jButton.setBounds(140,0,100,50);
        //jButton.addActionListener(to);
        jButton.setText("Zaloguj");
        jButton.setFocusable(false);
        jButton.setBorder(BorderFactory.createEtchedBorder());
        panel5.add(jButton);
    }
    private JTextArea createTextArea(String text) {
        JTextArea area = new JTextArea(text);
        area.setPreferredSize(new Dimension(210, 40)); // Ta komenda ustala wielkosc okna
        area.setWrapStyleWord(true);
        area.setForeground(new Color(0x000000));
        area.setFont(new Font("Consolas", Font.PLAIN, 35));
        area.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return area;
    }

    //odchaczanie - przyda sie do bulla

    //dla latwiejszego ogarniecia przyciskow, zaloguj zarejestroj, wróc
  /*  private JButton createButton(String text){
        JButton.setBackground(Color.white);
        JButton.setVisible(true);
        JButton.setLayout(null);
        JButton.setBounds(140,0,100,50);
        //jButton.addActionListener(to);
        JButton.setText(text);
        JButton.setFocusable(false);
        JButton.setBorder(BorderFactory.createEtchedBorder());

    }*/

    public JTextArea getTextArea() {
        return textArea;
    }

    public JTextArea getTextArea2() {
        return textArea2;
    }

    public JTextArea getTextArea3(){
        return textArea3;
    }

    public JTextArea getTextArea4(){
        return textArea4;
    }
    public JLabel getLabel2() {
        return label2;
    }

    public JPanel getPanel3() {
        return panel3;
    }

    public JPanel getPanel4() {
        return panel4;
    }

    public JPanel getPanel5() {
        return panel5;
    }
}
