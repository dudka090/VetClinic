package pl.grizzly.software.vetclinic.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Doctor {
    @Id
    private long id;
    private String name;
    private String surname;
    @OneToMany (orphanRemoval = true)
    private List<Appointment> appointments;
}
