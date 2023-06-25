package example.app.dbSettings;

import jakarta.persistence.*;

@Entity
@Table(name = "athlete")
public class Athlete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "uid")
    private Integer uid;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "birth_year")
    private Integer birthYear;

    public Athlete() {
    }

    public Athlete(Integer uid, String name, String surname, Integer birthYear) {
        this.uid = uid;
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
    public Integer getUid() {
        return uid;
    }
}
