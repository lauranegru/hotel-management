package hotel_management.hotel_manager.contract.views;

import hotel_management.hotel_manager.service.views.HotelView;

import java.util.UUID;

public class HotelViewGenerator {

    private static Integer uniqueId = 1;

    private UUID id;
    private String name;

    private HotelViewGenerator() {
        id(UUID.randomUUID());
        name("Hotel " + uniqueId);
        uniqueId++;
    }

    public static HotelViewGenerator hotelView() {
        return new HotelViewGenerator();
    }

    public HotelViewGenerator id(UUID id) {
        this.id = id;
        return this;
    }

    public HotelViewGenerator id(String id) {
        this.id = UUID.fromString(id);
        return this;
    }

    public HotelViewGenerator name(String name) {
        this.name = name;
        return this;
    }

    public HotelView build() {
        return new HotelView(id, name);
    }

}
