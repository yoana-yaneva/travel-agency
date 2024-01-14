package web.server.app.travelagency.service;

import web.server.app.travelagency.model.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationService {
    List<Reservation> findAllReservations();
    Optional<Reservation> findReservationById(Long id);
    Optional<Reservation> saveReservation(String name, String number, String holiday);
    Optional<Reservation> editReservation(Long id, String name, String number, String holiday);
    void deleteReservationById(Long id);
    List<Reservation> findReservationsByNumber(String number);
}
