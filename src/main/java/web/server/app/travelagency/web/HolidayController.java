package web.server.app.travelagency.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.server.app.travelagency.model.dto.HolidayDTO;
import web.server.app.travelagency.model.Holiday;
import web.server.app.travelagency.service.HolidayService;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/holidays")
public class HolidayController {

    private final HolidayService holidayService;

    public HolidayController(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    @GetMapping
    public ResponseEntity<List<Holiday>> findAll() {
        return ResponseEntity.ok(this.holidayService.findAllHolidays());
    }

    @GetMapping("{id}")
    public ResponseEntity<Holiday> findById(@PathVariable Long id) {
        return this.holidayService.findById(id)
                .map(holiday -> ResponseEntity.ok().body(holiday))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Holiday> save(@RequestBody HolidayDTO holidayDTO) {
        Holiday holiday = this.holidayService.saveHoliday(holidayDTO.getLocation(),
                holidayDTO.getTitle(),
                holidayDTO.getStartDate(),
                holidayDTO.getDuration(),
                holidayDTO.getPrice(),
                holidayDTO.getFreeSlots()).get();
        return ResponseEntity.ok(holiday);
    }

    @PutMapping
    public ResponseEntity<Holiday> edit(@RequestBody HolidayDTO holidayDTO) {
        Holiday holiday = this.holidayService.editHoliday(holidayDTO.getId(),
                holidayDTO.getLocation(),
                holidayDTO.getTitle(),
                holidayDTO.getStartDate(),
                holidayDTO.getDuration(),
                holidayDTO.getPrice(),
                holidayDTO.getFreeSlots()).get();
        return ResponseEntity.ok().body(holiday);
    }

    @GetMapping(params = {"location", "startDate", "duration"})
    public ResponseEntity<List<Holiday>> getAllByCriteria(@RequestParam String location,
                                                          @RequestParam String startDate,
                                                          @RequestParam Integer duration) {
        List<Holiday> holidays = this.holidayService.findHolidaysByIndicator(location, LocalDate.parse(startDate), duration);
        if (holidays.size() == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(holidays);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.holidayService.deleteHolidayById(id);
        if (this.holidayService.findById(id).isEmpty()) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
