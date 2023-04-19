package example;

import javax.swing.*;

public class NewTraining {

    public NewTraining(JPanel panel, JPanel panel2, JFrame to) {
        //Wylaczam stare panele
        panel.setEnabled(false); //to powinno zakryc poprzedniepane;e ale nie dziala z jakiegos powodu
        panel2.setEnabled(false);
        panel.setVisible(false);
        panel2.setVisible(false);
        System.out.println("tworzy sie newTraining");
    }
}
