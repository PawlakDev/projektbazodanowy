package example;

import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Scanner;

//import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "is_athlete") //true - zawodnik = mniejsze prawa, false - admin / trener = wieksze prawa
    private boolean isAthlete;

    public User() {
    }

    public User(String username, String email, String password, boolean isAthlete) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.isAthlete = isAthlete;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", pass=" + password + '\'' +
                ", athlete: " + isAthlete + '\'' +
                '}';
    }

    public static void addUser(SessionFactory sessionFactory, String login, String haslo, String email, boolean isAthlete) {

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        User user = new User(login, email, haslo, isAthlete);
        session.save(user);

        session.getTransaction().commit();
        System.out.println("Dodano użytkownika o ID: " + user.getId());
    }
}