package example;

import example.InfoFrames.LoginInfoFrameSettings;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Calendar;

//w tej klasie ma byc to co po nacisnieciu next w rejestracni, beda tu dalsze dane do uzupelnienia
public class RejestracjaData extends JFrame{
    JLabel labelTytul;
    JPanel Email, Name, Surname;
    boolean athlete;
    JTextArea email, name, surname;
    private JLayeredPane GraphicFrame;
    JComboBox<Integer> birthYear;

    RejestracjaData(JFrame to, JPanel panelTytul, JPanel Login, JPanel Password, JPanel frameBackground, JButton ButtonBack){

        System.out.println("rejestracja2");
        //Wylaczam stare panele
        Login.setVisible(false);
        Login.setVisible(false);
        Password.setVisible(false);
        Password.setVisible(false);

        //ustawiam label tytulowy
        labelTytul = new JLabel();
        LoginInfoFrameSettings wpisywanieTekstu = new LoginInfoFrameSettings(labelTytul,panelTytul, "Uzupelnij dane");

        // panel na pole tekstowe email
        Email = new JPanel();
        Email.setOpaque(false); // Ta obcja sluzy do ustawienia przezroczystego tla (niegenerowanie tla)
        Email.setBounds(-40, 20, 400, 120); // Ustaw wymiary i pozycję
        Email.setVisible(true);
        
        // pole tekstowe na email
        email = new JTextArea("email", 1, 20);
        email.setPreferredSize(new Dimension(10,12));
        email.setFont(new Font("MV Boli", 0, 16));
        email.setBorder(new LineBorder(Color.BLACK));
        email.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (email.getText().equals("")) {
                    email.setText("email");
                    email.setForeground(new Color(0, 0, 0, 128));
                }
            }

            @Override
            public void focusGained(FocusEvent e) {
                if (email.getText().equals("email")) {
                    email.setText("");
                    email.setForeground(new Color(0, 0, 0, 255)); // Ustawienie przezroczystości tekstu
                }
            }
        });

        // panel na pole tekstowe imie
        Name = new JPanel();
        Name.setOpaque(false); // Ta obcja sluzy do ustawienia przezroczystego tla (niegenerowanie tla)
        Name.setBounds(-40, 55, 400, 120); // Ustaw wymiary i pozycję
        Name.setVisible(true);

        // pole tekstowe na imie
        name = new JTextArea("imie", 1, 20);
        name.setPreferredSize(new Dimension(10,12));
        name.setFont(new Font("MV Boli", 0, 16));
        name.setBorder(new LineBorder(Color.BLACK));
        name.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (name.getText().equals("")) {
                    name.setText("imie");
                    name.setForeground(new Color(0, 0, 0, 128));
                }
            }

            @Override
            public void focusGained(FocusEvent e) {
                if (name.getText().equals("imie")) {
                    name.setText("");
                    name.setForeground(new Color(0, 0, 0, 255)); // Ustawienie przezroczystości tekstu
                }
            }
        });

        // panel na pole tekstowe imie
        Surname = new JPanel();
        Surname.setOpaque(false); // Ta obcja sluzy do ustawienia przezroczystego tla (niegenerowanie tla)
        Surname.setBounds(-40, 90, 400, 120); // Ustaw wymiary i pozycję
        Surname.setVisible(true);

        // pole tekstowe na nazwisko
        surname = new JTextArea("nazwisko", 1, 20);
        surname.setPreferredSize(new Dimension(10,12));
        surname.setFont(new Font("MV Boli", 0, 16));
        surname.setBorder(new LineBorder(Color.BLACK));
        surname.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (surname.getText().equals("")) {
                    surname.setText("nazwisko");
                    surname.setForeground(new Color(0, 0, 0, 128));
                }
            }

            @Override
            public void focusGained(FocusEvent e) {
                if (surname.getText().equals("nazwisko")) {
                    surname.setText("");
                    surname.setForeground(new Color(0, 0, 0, 255)); // Ustawienie przezroczystości tekstu
                }
            }
        });
        
        // ToggleButton do zaznaczania czy zawodnik czy trener
        JToggleButton changeFunct = new JToggleButton("zawodnik");
        ToggleButtonSettings ToggleButtonSettings = new ToggleButtonSettings(changeFunct,   310,25,70,40);
        changeFunct.setFont(new Font("Arial", Font.BOLD, 10));

        changeFunct.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 150, 200), 2),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        changeFunct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (changeFunct.isSelected()) {
                    changeFunct.setText("zawodnik");
                    athlete = true;

                } else {
                    changeFunct.setText("trener");
                    athlete = false;
                }
            }
        });
        changeFunct.setVisible(true);

        birthYear = new JComboBox<>();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int year = currentYear-8; year >= 1945; year--) {
            birthYear.addItem(year);
        }
        birthYear.setSelectedItem(2000);

        JLabel birthYearLabel = new JLabel("Rok urodzenia");
        birthYearLabel.setFont(new Font("Arial", Font.BOLD, 10));

        //Panel do roku
        JPanel birthYearPanel = new JPanel();
        birthYearPanel.setOpaque(false);
        birthYearPanel.setBounds(310, 80, 70, 40);
        birthYearPanel.setVisible(true);
        birthYearPanel.setLayout(new BorderLayout());
        birthYearPanel.add(birthYearLabel, BorderLayout.NORTH);
        birthYearPanel.add(birthYear, BorderLayout.CENTER);

        //button cofania updated
        ButtonBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == ButtonBack) {
                    System.out.println("ELO ELOOOO");
                }

                labelTytul.setText("Zarejestruj sie");
                changeFunct.setVisible(false);
                changeFunct.setEnabled(false);
                email.setVisible(false);
                email.setEnabled(false);
                name.setVisible(false);
                name.setEnabled(false);
                surname.setVisible(false);
                surname.setEnabled(false);
                birthYearPanel.setVisible(false);
                birthYearPanel.setEnabled(false);
            }
        });


        birthYearPanel.add(birthYear);
        Email.add(email);
        Name.add(name);
        Surname.add(surname);

        panelTytul.setVisible(true);

        GraphicFrame = new JLayeredPane();
        GraphicFrame.setBounds(60, 105, 400, 540);
        GraphicFrame.add(frameBackground, JLayeredPane.DEFAULT_LAYER);
        GraphicFrame.add(Email, JLayeredPane.PALETTE_LAYER);
        GraphicFrame.add(Name, JLayeredPane.PALETTE_LAYER);
        GraphicFrame.add(Surname, JLayeredPane.PALETTE_LAYER);
        GraphicFrame.add(changeFunct, JLayeredPane.PALETTE_LAYER);
        GraphicFrame.add(birthYearPanel, JLayeredPane.PALETTE_LAYER);

        to.add(panelTytul);
        to.add(GraphicFrame);
    }
}
