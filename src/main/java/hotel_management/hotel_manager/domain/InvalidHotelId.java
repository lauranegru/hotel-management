package hotel_management.hotel_manager.domain;

public class InvalidHotelId extends RuntimeException {

    public InvalidHotelId(String message) {
        super(message);
    }

}
