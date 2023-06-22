package example.app.dbSettings;

import jakarta.persistence.*;

@Entity
@Table(name = "workouts")
public class Workouts {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "idu")
    private int idUser;
    @Column(name = "date")
    private String date;
    @Column(name = "type")
    private String workouttype;
    @Column(name = "km")
    private int kilometers;
    @Column(name = "time")
    private int timeworkout;
    @Column(name = "description")
    String description;

    public Workouts() {
    }


    public Workouts(int idU, String date, String type, int km, int time, String opis) {
        this.idUser = idU;
        this.date = date;
        this.workouttype = type;
        this.timeworkout = time;
        this.kilometers = km;
        this.description = opis;
    }

    public Workouts(int idU, String date, String type, int time) {
        this.idUser = idUser;
        this.date = date;
        this.workouttype = workouttype;
        this.timeworkout = timeworkout;
        this.description = "";
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Integer getKilometers() {
        return kilometers;
    }

    public void setKilometers(Integer kilometers) {
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