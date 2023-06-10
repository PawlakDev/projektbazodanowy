package example;

import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Scanner;

//import javax.persistence.*;

@Entity
@Table(name = "workouts")
public class Workouts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "idU")
    private int idUser;

    @Column(name = "date")
    private String date;

    @Column(name = "type")
    private String workouttype;

    @Column(name = "km")
    private int kilometers;

    @Column(name = "time")
    private int timeworkout;

    public Workouts() {
    }



    public Workouts(int idU, String date, String type, int km, int time) {
        this.idUser = idUser;
        this.date = date;
        this.workouttype = workouttype;
        this.timeworkout = timeworkout;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWorkouttype() {
        return workouttype;
    }

    public void setWorkouttype(String workouttype) {
        this.workouttype = workouttype;
    }

    public int getKilometers() {
        return kilometers;
    }

    public void setKilometers(int kilometers) {
        this.kilometers = kilometers;
    }

    public int getTimeworkout() {
        return timeworkout;
    }

    public void setTimeworkout(int timeworkout) {
        this.timeworkout = timeworkout;
    }

    @Override
    public String toString() {
        return "Workouts{" +
                "id=" + id +
                ", idUser=" + idUser +
                ", date='" + date + '\'' +
                ", workouttype='" + workouttype + '\'' +
                ", kilometers=" + kilometers +
                ", timeworkout=" + timeworkout +
                '}';
    }

}