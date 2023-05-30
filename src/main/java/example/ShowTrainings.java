package example;

import org.hibernate.SessionFactory;

import javax.swing.*;
import java.awt.*;

public class ShowTrainings extends JFrame{
    private SessionFactory sessionFactory;

    JLayeredPane jLayeredPane;

    JFrame to;

    public ShowTrainings(SessionFactory sessionFactory) throws HeadlessException {
        this.sessionFactory = sessionFactory;
    }
}
