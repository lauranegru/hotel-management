package hotel_management.hotel_manager.domain;

public class InvalidHotelName extends RuntimeException {

    public InvalidHotelName(String message) {
        super(message);
    }

}
