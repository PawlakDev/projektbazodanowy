package example;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class MyFrame2 extends JFrame {
JLabel label2;
JPanel panel3, panel4, panel5;
JTextArea textArea, textArea2, textArea3, textArea4; //1 - login, 2 - haslo 3 - email, 4 - funkcja

    MyFrame2(JPanel panel, JPanel panel2, JFrame to, JButton jButton){

        //Wylaczam stare pazalnely
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

        panel4 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(getBackground());
                g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
            }
        };
        panel4.setBackground(new Color(200, 230, 255));
        panel4.setBounds(45, 110, 450, 100);
        panel4.setOpaque(false);
        panel4.setBorder(new LineBorder(new Color(100, 150, 200), 2, true));
        panel4.setVisible(true);
        to.add(panel4);


        //panel5
        panel5 = new JPanel();
        panel5.setBackground(Color.white);
        panel5.setBounds(80,210,355,80);
        panel5.setVisible(true);
        panel5.setLayout(null);
        //panel5.setBackground(Color.white);
        //panel.setBackground(Color.black);
        to.add(panel5);

        //textArea - login
        textArea = createTextArea("login");
        textArea.setForeground(new Color(0, 0, 0, 128)); // Ustawienie przezroczystości tekstu (128 - półprzezroczysty)
        textArea.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                    if(textArea.getText().equals("")) {
                        textArea.setText("login");
                        textArea.setForeground(new Color(0, 0, 0, 128)); // Ustawienie przezroczystości tekstu (128 - półprzezroczysty)
                    }
            }
            @Override
            public void focusGained(FocusEvent e) {
                if (textArea.getText().equals("login")) {
                    textArea.setText("");
                    textArea.setForeground(new Color(0, 0, 0, 255)); // Ustawienie przezroczystości tekstu
                }
            }


        });

        //Border border = BorderFactory.createLineBorder(Color.BLACK); // ustaw styl obramowania

        // Ustaw rozmiar dla JTextField
        panel4.setLayout(new GridBagLayout());

        panel4.add(textArea);

        //haslo
        textArea2.setText("haslo");
        textArea2.setForeground(new Color(0, 0, 0, 128)); // Ustawienie przezroczystości tekstu (128 - półprzezroczysty)
        textArea2.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if(textArea2.getText().equals("")) {
                    textArea2.setText("haslo");
                    textArea2.setForeground(new Color(0, 0, 0, 128)); // Ustawienie przezroczystości tekstu (128 - półprzezroczysty)
                }
            }
            @Override
            public void focusGained(FocusEvent e) {
                if (textArea2.getText().equals("haslo")) {
                    textArea2.setText("");
                    textArea2.setForeground(new Color(0, 0, 0, 255)); // Ustawienie przezroczystości tekstu
                }
            }

        });

        panel4.add(textArea2);

        //email
        textArea3 = createTextArea("email"); //nie wiem czy nie edzie trzeba zmienic polozenia

        //TickButton czyZawodnik = TickButton("");
        //panel4.add(textArea3);

        // JButton - Zaloguj
        //JButton jButton = new JButton();
        jButton.setBackground(new Color(200, 230, 255));
        jButton.setVisible(true);
        jButton.setLayout(null);
        jButton.setBounds(140, 20, 100, 50);
        jButton.setText("Zaloguj");
        jButton.setFocusable(false);
        jButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 150, 200), 2),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        jButton.setFont(new Font("Arial", Font.BOLD, 14));
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
