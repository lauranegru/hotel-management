package hotel_management.hotel_manager.domain;

import java.util.UUID;

public class HotelGenerator {

    private static Integer uniqueId = 1;

    private UUID id;
    private String name;

    private HotelGenerator() {
        id(UUID.randomUUID());
        name("Hotel " + uniqueId);
        uniqueId++;
    }

    public static Hotel anyHotel() {
        return hotel().build();
    }

    public static HotelGenerator hotel() {
        return new HotelGenerator();
    }

    public HotelGenerator id(UUID id) {
        this.id = id;
        return this;
    }

    public HotelGenerator id(String id) {
        this.id = UUID.fromString(id);
        return this;
    }

    public HotelGenerator name(String name) {
        this.name = name;
        return this;
    }

    public Hotel build() {
        return new Hotel(id, name);
    }

}
