package example.app.dbSettings;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@Entity
@Table(name = "athletes")
public class Athlete {
//    @OneToOne
//    @JoinColumn(name = "user_id")
//    private User user;
    @Id
    @Column(name="uid")
    private Integer uid;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "birth_year")
    private Integer birthYear;

    public Athlete() {
    }

    public Athlete(String name, String surname, Integer birthYear) {
        this.name = name;
        this.surname = surname;
        this.birthYear = birthYear;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public Integer getBirthyear() {
        return birthYear;
    }
}
