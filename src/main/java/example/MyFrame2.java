package example;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;



public class MyFrame2 extends JFrame {
JLabel label2;
JPanel panel3, panel4,panel5;
JTextArea textArea,textArea2, textArea3; //3 jest do rejestraji - email

    public JTextArea getTextArea3() {
        return textArea3;
    }

    public void setTextArea3(JTextArea textArea3) {
        this.textArea3 = textArea3;
    }

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

            textArea = new JTextArea();
            textArea.setText("login");
            textArea.setPreferredSize(new Dimension(210, 40)); // Ta komenda ustala wielkosc okna
    //textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);

    Border border = BorderFactory.createLineBorder(Color.BLACK); // ustaw styl obramowania
    // Ustaw rozmiar dla JTextField
    //panel4.setLayout(new GridBagLayout());
    //textArea.setBackground(Color.gray);
            textArea.setBorder(border);
            textArea.setForeground(new Color(0x000000));
            textArea.setFont(new Font("Consolas", Font.PLAIN,35));
            textArea.setBounds(0, 0, 340, 60);

            panel4.add(textArea);

    //

    textArea2 = new JTextArea();
            textArea2.setBorder(border);
            textArea2.setText("haslo");
            textArea2.setPreferredSize(new Dimension(210, 40)); // Ta komenda ustala wielkosc okna
    //textArea.setLineWrap(true);
            textArea2.setWrapStyleWord(true);

    // Ustaw rozmiar dla JTextField
    //panel4.setLayout(new GridBagLayout());
            textArea2.setForeground(new Color(0x000000));
            textArea2.setFont(new Font("Consolas", Font.PLAIN,35));
            textArea2.setBounds(0, 0, 340, 60);

            panel4.add(textArea2);
    //
        textArea3 = new JTextArea();//kinda shitty i do uporzadkowania te text area w weekend sie tym zajme
        textArea3 = new JTextArea();
        textArea3.setText("email");
        textArea3.setPreferredSize(new Dimension(210, 40)); // Ta komenda ustala wielkosc okna
        //textArea.setLineWrap(true);
        textArea3.setWrapStyleWord(true);
        textArea3.setForeground(new Color(0x000000));
        textArea3.setFont(new Font("Consolas", Font.PLAIN,35));
        textArea3.setBounds(0, 0, 340, 60);
//nowy panel trzeba dodac na rejesracje
       // panel4.add(textArea3);

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

    public JTextArea getTextArea() {
        return textArea;
    }

    public JTextArea getTextArea2() {
        return textArea2;
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
