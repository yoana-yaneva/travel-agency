package web.server.app.travelagency.model.dto;

public class searchReservationDTO {
    private String phoneNumber;

    public searchReservationDTO() {
    }

    public searchReservationDTO(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
