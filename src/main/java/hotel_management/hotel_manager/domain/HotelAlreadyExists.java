package hotel_management.hotel_manager.domain;

public class HotelAlreadyExists extends RuntimeException {

    public HotelAlreadyExists() {
        super("The hotel with the given id already exists");
    }

    public HotelAlreadyExists(String message) {
        super(message);
    }

}
