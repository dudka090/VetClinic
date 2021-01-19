package pl.grizzly.software.vetclinic.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.grizzly.software.vetclinic.model.Appointment;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;


@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Appointment save (Appointment appointment);
    boolean existsByDoctorIdAndDate(Long id, LocalDate date);
    Optional<Appointment> findByDoctorIdAndDateAndTime(Long id, LocalDate date, LocalTime time);
    List<Appointment> findAllByDoctorIdAndDate(Long id, LocalDate date);
    boolean existsById(Long id);
    void deleteById(Long id);

}
