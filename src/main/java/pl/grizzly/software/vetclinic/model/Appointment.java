package pl.grizzly.software.vetclinic.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@NoArgsConstructor
@Data
public class Appointment {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Owner owner;
    @ManyToOne(fetch = FetchType.LAZY)
    private Doctor doctor;
    private LocalDate date;
    private LocalTime time;

    public Appointment(Owner owner, Doctor doctor, LocalDate date, LocalTime time) {
        this.owner = owner;
        this.doctor = doctor;
        this.date = date;
        this.time = time;
    }
}
