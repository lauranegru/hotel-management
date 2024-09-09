package hotel_management.hotel_manager.domain;

public class HotelNotFound extends RuntimeException {

    public HotelNotFound() {
        super("The hotel with the given id does not exist");
    }

}
