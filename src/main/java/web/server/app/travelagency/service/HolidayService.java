package web.server.app.travelagency.service;

import web.server.app.travelagency.model.Holiday;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface HolidayService {
    List<Holiday> findAllHolidays();
    List<Holiday> findHolidaysByIndicator(String locationName, LocalDate startDate, Integer duration);
    Optional<Holiday> findById(Long id);
    Optional<Holiday> saveHoliday(String location, String title, LocalDate startDate, Integer duration, Double price, Integer freeSlots);
    Optional<Holiday> editHoliday(Long id, String location, String title, LocalDate startDate, Integer duration, Double price, Integer freeSlots);
    void deleteHolidayById(Long id);
}