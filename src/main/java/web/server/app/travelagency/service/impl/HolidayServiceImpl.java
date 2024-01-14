package web.server.app.travelagency.service.impl;

import org.springframework.stereotype.Service;
import web.server.app.travelagency.model.Holiday;
import web.server.app.travelagency.model.Location;
import web.server.app.travelagency.repository.HolidayRepository;
import web.server.app.travelagency.repository.LocationRepository;
import web.server.app.travelagency.service.HolidayService;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class HolidayServiceImpl implements HolidayService {
    private final HolidayRepository holidayRepository;
    private final LocationRepository locationRepository;

    public HolidayServiceImpl(HolidayRepository holidayRepository, LocationRepository locationRepository) {
        this.holidayRepository = holidayRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Holiday> findAllHolidays() {
        return this.holidayRepository.findAll();
    }

    @Override
    public List<Holiday> findHolidaysByIndicator(String locationName, LocalDate startDate, Integer duration) {
        Optional<Location> locationByCity = this.locationRepository.findLocationByCityName(locationName);
        if (locationByCity.isEmpty()) {
            List<Location> locationByCountry = this.locationRepository.findLocationByCountryName(locationName);
            if (locationByCountry.isEmpty()) {
                return Collections.emptyList();
            } else {
                for (Location current : locationByCountry) {
                    List<Holiday> holidays = this.holidayRepository.findHolidayByholidayLocationAndStartDateOfHolidayAndDuration(current,
                            startDate, duration);
                    if (!holidays.isEmpty()) {
                        return holidays;
                    }
                }
                return Collections.emptyList();
            }
        } else {
            return this.holidayRepository.findHolidayByholidayLocationAndStartDateOfHolidayAndDuration(locationByCity.get(),
                    startDate, duration);
        }
    }

    @Override
    public Optional<Holiday> findById(Long id) {
        return this.holidayRepository.findById(id);
    }

    @Override
    public Optional<Holiday> saveHoliday(String location, String title, LocalDate startDate, Integer duration, Double price, Integer freeSlots) {
        Location existingLocation = locationRepository.findById(Long.valueOf(location)).orElseThrow(RuntimeException::new);
        Holiday holiday = new Holiday(title, startDate, duration, price, freeSlots, existingLocation);
        holidayRepository.save(holiday);
        return Optional.of(holiday);
    }

    @Override
    public Optional<Holiday> editHoliday(Long id, String location, String title, LocalDate startDate, Integer duration, Double price, Integer freeSlots) {
        Location existingLocation = locationRepository.findById(Long.valueOf(location)).orElseThrow(RuntimeException::new);
        Holiday holiday = this.holidayRepository.findById(id).get();
        holiday.setHolidayLocation(existingLocation);
        holiday.setTitleHoliday(title);
        holiday.setStartDateOfHoliday(startDate);
        holiday.setDuration(duration);
        holiday.setTotalPrice(price);
        holiday.setFreeSlots(freeSlots);

        this.holidayRepository.save(holiday);
        return Optional.of(holiday);
    }

    @Override
    public void deleteHolidayById(Long id) {
        this.holidayRepository.deleteById(id);
    }
}
