package hotel_management.hotel_manager.domain;

import java.util.UUID;

import static hotel_management.hotel_manager.domain.HotelIdGenerator.anyHotelIdValue;
import static hotel_management.hotel_manager.domain.HotelNameGenerator.anyHotelNameValue;

public class HotelGenerator {

    private HotelId id;
    private HotelName name;

    private HotelGenerator() {
        id(anyHotelIdValue());
        name(anyHotelNameValue());
    }

    public static Hotel anyHotel() {
        return hotel().build();
    }

    public static HotelGenerator hotel() {
        return new HotelGenerator();
    }

    public HotelGenerator id(UUID id) {
        this.id = HotelId.of(id);
        return this;
    }

    public HotelGenerator id(String id) {
        this.id = HotelId.of(UUID.fromString(id));
        return this;
    }

    public HotelGenerator id(HotelId id) {
        this.id = id;
        return this;
    }

    public HotelGenerator name(String name) {
        this.name = HotelName.of(name);
        return this;
    }

    public Hotel build() {
        return new Hotel(id, name);
    }

}
