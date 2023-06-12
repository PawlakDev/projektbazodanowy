package example.app.dbSettings;

import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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

    @Column(name = "is_coach") //true - zawodnik = mniejsze prawa, false - admin / trener = wieksze prawa
    private boolean isCoach;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "birth_year")
    private Integer birthYear;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.email = "default@email.com";
        this.isCoach = false;
    }

    public User(String username, String password, String email, boolean isCoach, String name, String surname, Integer birthYear) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.isCoach = isCoach;
        this.name = name;
        this.surname = surname;
        this.birthYear = birthYear;
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

    public boolean getIscoach() {
        return isCoach;
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
                ", password='" + password + '\'' +
                ", isCoach=" + isCoach +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthYear=" + birthYear +
                '}';
    }

    //wyzwalacz co to
    public static void addUser(SessionFactory sessionFactory, String login, String haslo) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        User user = new User(login, haslo);
        session.save(user);
        session.getTransaction().commit();
        System.out.println("Dodano użytkownika o ID: " + user.getId());
    }
     public static void addUser(SessionFactory sessionFactory, String login, String haslo, String email, boolean isCoach, String name, String surname, Integer birthYear){
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            User user = new User(login, haslo, email, isCoach, name, surname, birthYear);
            session.save(user);
            System.out.println("ciekawe czy sie dodaje");
            session.getTransaction().commit();
            System.out.println("Dodano użytkownika o ID: " + user.getId());
        }
    }
