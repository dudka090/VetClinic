package pl.grizzly.software.vetclinic.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.grizzly.software.vetclinic.model.Appointment;
import pl.grizzly.software.vetclinic.model.Doctor;
import pl.grizzly.software.vetclinic.model.Owner;
import pl.grizzly.software.vetclinic.repository.AppointmentRepository;
import pl.grizzly.software.vetclinic.repository.DoctorRepository;
import pl.grizzly.software.vetclinic.repository.OwnerRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;


@Service
public class VetClinicService {
    OwnerRepository ownerRepository;
    DoctorRepository doctorRepository;
    AppointmentRepository appointmentRepository;
    DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    DateTimeFormatter timeformatter = DateTimeFormatter.ofPattern("HH:mm");
    @Autowired
    public VetClinicService(OwnerRepository ownerRepository, DoctorRepository doctorRepository, AppointmentRepository appointmentRepository) {
        this.ownerRepository = ownerRepository;
        this.doctorRepository = doctorRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public ResponseEntity<String> cancelAppointment(long ownerId, long pin, long appointmentId){
        if(validateIdAndPin(ownerId,pin)){
            if(this.ownerRepository.existsById(ownerId)){
                Owner owner = this.ownerRepository.findById(ownerId);
                if(owner.getPin() == pin){
                    if(this.appointmentRepository.existsById(appointmentId)){
                        this.appointmentRepository.deleteById(appointmentId);
                        return new ResponseEntity<>("Visit deleted", HttpStatus.OK);
                    }else{
                        return new ResponseEntity<>("There is no visit with such id", HttpStatus.BAD_REQUEST);
                    }
                }else{
                    return new ResponseEntity<>("Wrong PIN", HttpStatus.FORBIDDEN);
                }
            }else{
                return new ResponseEntity<>("Owner with such id doesn't exist", HttpStatus.BAD_REQUEST);
            }
        }else {
            return new ResponseEntity<>("The ID and PIN must consist of 4 digits", HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<String> listAppointmentsForDay(long doctorId, String date){
        if(this.doctorRepository.existsById(doctorId)){
            LocalDate localDate  = LocalDate.parse(date, dateformatter);
            if(this.appointmentRepository.existsByDoctorIdAndDate(doctorId, localDate)){
                List<Appointment> appointments = this.appointmentRepository.findAllByDoctorIdAndDate(doctorId, localDate);
                StringBuilder stringBuilder = new StringBuilder("Visits for a day: " + localDate + ":\n");
                for (Appointment a:appointments
                     ) {
                    stringBuilder.append(a.getDate()).append(" ").append(a.getTime()).append("     Client: ").append(a.getOwner().getName()).append(" ").append(a.getOwner().getSurname()).append("\n");
                }
                return new ResponseEntity<>(stringBuilder.toString(), HttpStatus.OK);
            }else{
                return new ResponseEntity<>("No visits for such day", HttpStatus.OK);
            }


        }else{
            return new ResponseEntity<>("Doctor with such id doesn't exist", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> makeAppointment(long ownerId, long pin, long doctorId, String date, String time){
        if(validateIdAndPin(ownerId,pin)){
            if(this.ownerRepository.existsById(ownerId)){
                Owner owner = this.ownerRepository.findById(ownerId);
                if(owner.getPin() == pin){
                    if(this.doctorRepository.existsById(doctorId)){
                        LocalDate localDate = LocalDate.parse(date, dateformatter);
                        LocalTime localTime = LocalTime.parse(time, timeformatter);
                        Optional<Appointment> appointmentOccupied = this.appointmentRepository.findByDoctorIdAndDateAndTime(doctorId, localDate, localTime);
                        if(appointmentOccupied.isEmpty()){
                            Doctor doctor = this.doctorRepository.findById(doctorId).get();
                            Appointment appointment = this.appointmentRepository.save(new Appointment(owner, doctor, localDate, localTime));
                            return new ResponseEntity<>("Visit was made\nDate: " +appointment.getDate() + "\nTime: " + appointment.getTime() + "\n Visit ID:  " +
                                    appointment.getId() + "\n Doctor: " + appointment.getDoctor().getName() + " " + appointment.getDoctor().getSurname(), HttpStatus.OK);
                        }else{
                            return new ResponseEntity<>("Already booked", HttpStatus.FORBIDDEN);
                        }
                    }
                    else{
                        return new ResponseEntity<>("Doctor with such id doesn't exist", HttpStatus.BAD_REQUEST);
                    }
                }
                else{
                    return new ResponseEntity<>("Wrong PIN", HttpStatus.FORBIDDEN);
                }
            }
            else{return new ResponseEntity<>("Owner with such id doesn't exist", HttpStatus.BAD_REQUEST);}
        }else {
            return new ResponseEntity<>("The ID and PIN must consist of 4 digits", HttpStatus.BAD_REQUEST);
        }
    }

    private boolean validateIdAndPin(long id, long pin){
        if(String.valueOf(id).length()==4&&String.valueOf(pin).length()==4){
            return true;
        }
        return false;
    }


}
