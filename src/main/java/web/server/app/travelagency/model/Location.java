package web.server.app.travelagency.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String streetName;
    private Integer number;
    private String cityName;
    private String countryName;

    public Location() {
    }

    public Location(String street, Integer number, String city, String countryName) {
        this.streetName = street;
        this.number = number;
        this.cityName = city;
        this.countryName = countryName;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }
    public void setNumber(Integer number) {
        this.number = number;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
