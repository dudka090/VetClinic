package pl.grizzly.software.vetclinic.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.grizzly.software.vetclinic.model.Doctor;
import java.util.Optional;


@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    boolean existsById(Long id);
    Optional<Doctor> findById(Long id);

}
