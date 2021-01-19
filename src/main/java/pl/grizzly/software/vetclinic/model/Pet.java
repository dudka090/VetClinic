package pl.grizzly.software.vetclinic.model;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@Data
public class Pet {
    @Id
    private long id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    private Owner owner;

}
