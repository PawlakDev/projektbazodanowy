package example.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusAdapter;

public class textArea extends JTextArea{
    private String textHolder;

    private String name;


    public textArea(String name, String text) {
        super(text);
        this.textHolder = text;
        this.name = name;
        setPreferredSize(new Dimension(210, 40)); // Ta komenda ustala wielkosc okna
        setWrapStyleWord(true);
        setForeground(new Color(0, 0, 0, 128));
        setFont(new Font("MV Boli", 0, 32));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (getText().equals(name)) {
                    setText(name);
                    setForeground(new Color(0, 0, 0, 128));
                }
            }

            @Override
            public void focusGained(FocusEvent e) {
                if (getTextHolder().equals(text)) {
                    setText("");
                    setForeground(new Color(0, 0, 0, 255)); // Ustawienie przezroczystości tekstu - nieprzezroczysty
                }
            }
        });
    }

    public String getTextHolder() {
        return textHolder;
    }

}

