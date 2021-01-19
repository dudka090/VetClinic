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
public class Owner {
    @Id
    private long id;
    private long pin;
    private String name;
    private String surname;
    private String address;
    private String telephone;
    @OneToMany (orphanRemoval = true)
    private List<Pet> pets;
    @OneToMany (orphanRemoval = true)
    private List<Appointment> appointments;
}
