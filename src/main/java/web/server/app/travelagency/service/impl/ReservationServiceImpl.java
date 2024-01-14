package web.server.app.travelagency.service.impl;

import org.springframework.stereotype.Service;
import web.server.app.travelagency.model.Holiday;
import web.server.app.travelagency.model.Reservation;
import web.server.app.travelagency.repository.HolidayRepository;
import web.server.app.travelagency.repository.ReservationRepository;
import web.server.app.travelagency.service.ReservationService;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final HolidayRepository holidayRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository, HolidayRepository holidayRepository) {
        this.reservationRepository = reservationRepository;
        this.holidayRepository = holidayRepository;
    }

    @Override
    public List<Reservation> findAllReservations() {
        return this.reservationRepository.findAll();
    }

    @Override
    public Optional<Reservation> findReservationById(Long id) {
        return this.reservationRepository.findById(id);
    }

    @Override
    public Optional<Reservation> saveReservation(String name, String number, String holiday) {
        Holiday usedHoliday = this.holidayRepository.findById(Long.valueOf(holiday)).orElseThrow(RuntimeException::new);
        Reservation reservation = new Reservation(name, number, usedHoliday);
        this.reservationRepository.save(reservation);
        return Optional.of(reservation);
    }

    @Override
    public Optional<Reservation> editReservation(Long id, String name, String number, String holiday) {
        Holiday existingHoliday = this.holidayRepository.findById(Long.valueOf(holiday)).orElseThrow(RuntimeException::new);
        Optional<Reservation> optionalReservation = this.reservationRepository.findById(id);
        if (!optionalReservation.isPresent()) {
            return Optional.empty();
        }
        Reservation reservation = optionalReservation.get();
        reservation.setName(name);
        reservation.setNumber(number);
        reservation.setHoliday(existingHoliday);
        this.reservationRepository.save(reservation);
        return Optional.of(reservation);
    }

    @Override
    public void deleteReservationById(Long id) {
        this.reservationRepository.deleteById(id);
    }

    @Override
    public List<Reservation> findReservationsByNumber(String number) {
        return this.reservationRepository.findAllByNumber(number);
    }
}
