package hotel_management.hotel_manager.domain;

import java.util.UUID;

public class HotelIdGenerator {

    public static HotelId anyHotelId() {
        return HotelId.of(UUID.randomUUID());
    }

    public static UUID anyHotelIdValue() {
        return anyHotelId().value();
    }

}
