package hotel_management.hotel_manager.domain;

public class HotelAlreadyExists extends RuntimeException {

    public HotelAlreadyExists(String message) {
        super(message);
    }

}
