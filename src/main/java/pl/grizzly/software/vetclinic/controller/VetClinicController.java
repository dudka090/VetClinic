package pl.grizzly.software.vetclinic.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.grizzly.software.vetclinic.service.VetClinicService;

@RestController
public class VetClinicController {
    VetClinicService vetClinicService;
    @Autowired
    public VetClinicController(VetClinicService vetClinicService) {
        this.vetClinicService = vetClinicService;
    }

    @PostMapping("/newAppointment")
    public ResponseEntity<String> makeAppointment(@RequestParam long ownerId,
                                                  @RequestParam long pin,
                                                  @RequestParam long doctorId,
                                                  @RequestParam String date,
                                                  @RequestParam String time){
        return  this.vetClinicService.makeAppointment(ownerId,pin, doctorId, date, time);
    }

    @GetMapping("/listAppointmentsForDay")
    public ResponseEntity<String> listAppointmentsForDay(@RequestParam long doctorId,
                                  @RequestParam String date
    ){
        return  this.vetClinicService.listAppointmentsForDay(doctorId,date);
    }

    @DeleteMapping("/deleteAppointment")
    public ResponseEntity<String> cancelAppointment(@RequestParam long ownerId,
                                          @RequestParam long pin,
                                          @RequestParam long appointmentId
    ){
        return this.vetClinicService.cancelAppointment(ownerId,pin, appointmentId);
    }

}
