package pl.grizzly.software.vetclinic.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
public class VetClinicController {

    @PostMapping("/newAppointment")
    public String makeAppointment(@RequestParam int ownerId,
                                          @RequestParam int pin,
                                          @RequestParam int doctorId,
                                          @RequestParam String dateTime
                                          ){
        return  "Owner: " + ownerId + ", PIN: " + pin + ", DoctorID: " + doctorId + ", Data: " + dateTime;
    }

    @GetMapping("/listAppointmentsForDay")
    public String makeAppointment(@RequestParam int doctorId,
                                  @RequestParam String date
    ){
        return  "DoctorID: " + doctorId + ", Data: " + date;
    }

    @DeleteMapping("/deleteAppointment")
    public String makeAppointment(@RequestParam int ownerId,
                                  @RequestParam int pin,
                                  @RequestParam int appointmentId
    ){
        return  "Owner: " + ownerId + ", PIN: " + pin + "Appointment: " + appointmentId;
    }

}
