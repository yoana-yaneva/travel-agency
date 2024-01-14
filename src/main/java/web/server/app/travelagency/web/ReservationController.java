package web.server.app.travelagency.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.server.app.travelagency.model.dto.searchReservationDTO;
import web.server.app.travelagency.model.dto.ReservationDTO;
import web.server.app.travelagency.model.Reservation;
import web.server.app.travelagency.service.ReservationService;

import java.util.List;

@RestController
@CrossOrigin("*")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/reservations")
    public ResponseEntity<List<Reservation>> getAll() {
        return ResponseEntity.ok(this.reservationService.findAllReservations());
    }

    @GetMapping("/reservations/{id}")
    public ResponseEntity<Reservation> findById(@PathVariable Long id) {
        return this.reservationService.findReservationById(id)
                .map(reservation -> ResponseEntity.ok().body(reservation))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/reservations")
    public ResponseEntity<Reservation> edit(@RequestBody ReservationDTO reservationDTO) {
        Reservation reservation = this.reservationService.editReservation(reservationDTO.getId(),
                reservationDTO.getContactName(),
                reservationDTO.getPhoneNumber(),
                reservationDTO.getHoliday()).get();
        return ResponseEntity.ok().body(reservation);
    }

    @PostMapping("/reservations")
    public ResponseEntity<Reservation> save(@RequestBody ReservationDTO reservationDTO) {
        Reservation reservation = this.reservationService.saveReservation(reservationDTO.getContactName(),
                reservationDTO.getPhoneNumber(),
                reservationDTO.getHoliday()).get();
        return ResponseEntity.ok(reservation);
    }

    @GetMapping("/find-reservation")
    public ResponseEntity<List<Reservation>> findByNumber(@RequestBody searchReservationDTO reservationDTO) {
        return ResponseEntity.ok(this.reservationService.findReservationsByNumber(reservationDTO.getPhoneNumber()));
    }

    @DeleteMapping("/reservations/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.reservationService.deleteReservationById(id);
        if (!this.reservationService.findReservationById(id).isPresent()) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
