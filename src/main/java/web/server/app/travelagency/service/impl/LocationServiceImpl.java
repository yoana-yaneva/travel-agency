package web.server.app.travelagency.service.impl;

import org.springframework.stereotype.Service;
import web.server.app.travelagency.model.Location;
import web.server.app.travelagency.repository.LocationRepository;
import web.server.app.travelagency.service.LocationService;

import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Location> findAllLocations() {
        return this.locationRepository.findAll();
    }

    @Override
    public Optional<Location> findLocationById(Long id) {
        return this.locationRepository.findById(id);
    }

    @Override
    public Optional<Location> saveLocation(String street, Integer number, String city, String country) {
        Location location = new Location(street, number, city, country);
        locationRepository.save(location);
        return Optional.of(location);
    }

    @Override
    public Optional<Location> editLocation(Long id, String street, Integer number, String city, String country) {
        Location location = this.locationRepository.findById(id).get();
        location.setStreetName(street);
        location.setNumber(number);
        location.setCityName(city);
        location.setCountryName(country);

        this.locationRepository.save(location);
        return Optional.of(location);
    }

    @Override
    public void deleteLocationById(Long id) {
        this.locationRepository.deleteById(id);
    }
}
