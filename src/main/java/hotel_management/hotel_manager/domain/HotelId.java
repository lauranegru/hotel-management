package hotel_management.hotel_manager.domain;

import java.util.UUID;

public class HotelId {

    private final UUID value;

    private HotelId(UUID value) {
        if (value == null)
            throw new InvalidHotelId("The hotel id should not be null");

        this.value = value;
    }

    public static HotelId of(UUID value) {
        return new HotelId(value);
    }

    public UUID value() {
        return value;
    }

}
