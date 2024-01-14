package web.server.app.travelagency.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Holiday {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titleHoliday;
    private LocalDate startDateOfHoliday;
    private Integer duration;
    private Double totalPrice;
    private Integer freeSlots;
    @ManyToOne
    private Location holidayLocation;

    public Holiday() {
    }

    public Holiday(String title, LocalDate startDateOfHoliday, Integer duration, Double totalPrice, Integer freeSlots, Location holidayLocation) {
        this.titleHoliday = title;
        this.startDateOfHoliday = startDateOfHoliday;
        this.duration = duration;
        this.totalPrice = totalPrice;
        this.freeSlots = freeSlots;
        this.holidayLocation = holidayLocation;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setTitleHoliday(String titleHoliday) {
        this.titleHoliday = titleHoliday;
    }
    public void setStartDateOfHoliday(LocalDate startDateOfHoliday) {
        this.startDateOfHoliday = startDateOfHoliday;
    }
    public void setDuration(Integer duration) {
        this.duration = duration;
    }
    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
    public void setFreeSlots(Integer freeSlots) {
        this.freeSlots = freeSlots;
    }
    public Location getHolidayLocation() {
        return holidayLocation;
    }
    public void setHolidayLocation(Location holidayLocation) {
        this.holidayLocation = holidayLocation;
    }
}
