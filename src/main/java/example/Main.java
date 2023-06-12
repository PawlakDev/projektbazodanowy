package example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            configuration.configure();
            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory;
    }

    public static void main(String[] args) {
        final int debugMode = 0;//final to stala

        //Ustawienia hibernate
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Start start = new Start(sessionFactory);

        if(debugMode == 0) {

        Scanner scanner = new Scanner(System.in);

            int choice;
            do {
                System.out.println("--------------------------------------------------------");
                System.out.println("------------------        MENU:        -----------------");
                System.out.println("--------------------------------------------------------");
                System.out.println("1. Dodaj studenta do bazy danych");
                System.out.println("2. Usuń studenta z bazy danych");
                System.out.println("3. Zaktualizuj studenta w bazie danych");
                System.out.println("4. Wyświetl wszystkich studentów z bazy danych");
                System.out.println("5. Wyszukaj danego studenta z bazy danych");
                System.out.println("0. Wyście");
                System.out.println("--------------------------------------------------------");
                System.out.println("--------------------------------------------------------");
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {

                    case 1:
                        //addUser(sessionFactory, scanner);
                        break;
                    case 2:
                        deleteUser(sessionFactory, scanner);
                        break;
                    case 3:
                        modifyUser(sessionFactory, scanner);
                        break;
                    case 4:
                        displayUsers(sessionFactory);
                        break;
                    case 5:
                        searchUser(sessionFactory, scanner);
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Zły wybór");
                        break;
                }
            } while (choice != 0);
        }
    }

    private static void deleteUser(SessionFactory sessionFactory, Scanner scanner) {
        System.out.println("Podaj ID użytkownika do usunięcia: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        User user = session.get(User.class, id);
        if (user != null) {
            session.delete(user);
            System.out.println("Usunięto użytkownika o ID: " + id);
        } else {
            System.out.println("Nie znaleziono użytkownika o ID: " + id);
        }

        session.getTransaction().commit();
    }

    private static void searchUser(SessionFactory sessionFactory, Scanner scanner) {
        System.out.println("Podaj imię użytkownika: ");
        String name = scanner.nextLine();

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        List<User> users = session.createQuery("from User", User.class).getResultList();

        System.out.println("Użytkownicy w bazie danych:");
        for (User user : users) {
            if (user.getUsername().equals(name)) {
                System.out.println(user);
            }
        }

        session.getTransaction().commit();
    }

    //raczej do usuniecia
//    private static void addUser(SessionFactory sessionFactory, Scanner scanner) {
//
//        System.out.println("Podaj login użytkownika: ");
//        String username = scanner.nextLine();
//
//        System.out.println("Podaj email użytkownika: ");
//        String email = scanner.nextLine();
//
//        System.out.println("Podaj hasło użytkownika: ");
//        String password = scanner.nextLine();
//
//        Session session = sessionFactory.getCurrentSession();
//        session.beginTransaction();
//
//        User user = new User(username, email, password, true);
//        session.save(user);
//
//        session.getTransaction().commit();
//        System.out.println("Dodano użytkownika o ID: " + user.getId());
//    }


    private static void modifyUser(SessionFactory sessionFactory, Scanner scanner) {
        System.out.println("Podaj ID użytkownika do modyfikacji: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        User user = session.get(User.class, id);
        if (user != null) {
            System.out.println("Aktualny login: " + user.getUsername());
            System.out.println("Podaj nowy login (lub naciśnij enter, aby pominąć): ");
            String username = scanner.nextLine();
            if (!username.isBlank()) {
                user.setUsername(username);
            }

            System.out.println("Aktualny Email: " + user.getEmail());
            System.out.println("Podaj nowy Email (lub naciśnij enter, aby pominąć): ");
            String email = scanner.nextLine();
            if (!email.isBlank()) {
                user.setEmail(email);
            }

            System.out.println("Aktualny haslo: " + user.getPassword());
            System.out.println("Podaj nowe hasło (lub naciśnij enter, aby pominąć): ");
            String pass = scanner.nextLine();
            if (!pass.isBlank()) {
                user.setPassword(pass);
            }

            session.getTransaction().commit();
            System.out.println("Zaktualizowano użytkownika o ID: " + id);
        } else {
            System.out.println("Nie znaleziono użytkownika o ID: " + id);
        }
    }

    private static void displayUsers(SessionFactory sessionFactory) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        List<User> users = session.createQuery("from User", User.class).getResultList();

        System.out.println("Użytkownicy w bazie danych:");
        for (User user : users) {
            System.out.println(user);
        }

        session.getTransaction().commit();
    }
}

