package web.server.app.travelagency.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.server.app.travelagency.model.dto.LocationDTO;
import web.server.app.travelagency.model.Location;
import web.server.app.travelagency.service.LocationService;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/locations")
public class LocationController {
    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public ResponseEntity<List<Location>> findAll() {
        return ResponseEntity.ok(this.locationService.findAllLocations());
    }

    @GetMapping("{id}")
    public ResponseEntity<Location> findById(@PathVariable Long id) {
        return this.locationService.findLocationById(id)
                .map(location -> ResponseEntity.ok().body(location))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<Location> edit(@RequestBody LocationDTO locationDTO) {
        Location location = this.locationService.editLocation(locationDTO.getId(),
                locationDTO.getStreet(),
                locationDTO.getNumber(),
                locationDTO.getCity(),
                locationDTO.getCountry()).get();
        return ResponseEntity.ok().body(location);
    }

    @PostMapping
    public ResponseEntity<Location> save(@RequestBody LocationDTO locationDTO) {
        Location location = this.locationService.saveLocation(locationDTO.getStreet(),
                locationDTO.getNumber(),
                locationDTO.getCity(),
                locationDTO.getCountry()).get();
        return ResponseEntity.ok(location);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.locationService.deleteLocationById(id);
        if (this.locationService.findLocationById(id).isEmpty()) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
