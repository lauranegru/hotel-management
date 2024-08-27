package hotel_management.hotel_manager.domain;

public class HotelNotFound extends RuntimeException {

    public HotelNotFound(String message) {
        super(message);
    }

}
