package web.server.app.travelagency.service;

import web.server.app.travelagency.model.Location;

import java.util.List;
import java.util.Optional;

public interface LocationService {
    List<Location> findAllLocations();
    Optional<Location> findLocationById(Long id);
    Optional<Location> saveLocation(String street, Integer number, String city, String country);
    Optional<Location> editLocation(Long id, String street, Integer number, String city, String country);
    void deleteLocationById(Long id);
}
