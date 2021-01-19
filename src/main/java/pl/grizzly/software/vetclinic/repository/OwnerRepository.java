package pl.grizzly.software.vetclinic.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.grizzly.software.vetclinic.model.Owner;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    boolean existsById(long id);
    Owner findById(long id);
}
