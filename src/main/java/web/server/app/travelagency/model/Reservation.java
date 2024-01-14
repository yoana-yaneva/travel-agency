package web.server.app.travelagency.model;

import javax.persistence.*;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String number;
    @ManyToOne
    private Holiday holiday;

    public Reservation() {
    }

    public Reservation(String name, String number, Holiday holiday) {
        this.name = name;
        this.number = number;
        this.holiday = holiday;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public void setHoliday(Holiday holiday) {
        this.holiday = holiday;
    }
}
