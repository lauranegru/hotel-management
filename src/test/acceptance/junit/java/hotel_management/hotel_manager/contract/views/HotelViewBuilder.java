package hotel_management.hotel_manager.contract.views;

import hotel_management.hotel_manager.service.views.HotelView;

import java.util.UUID;

public class HotelViewBuilder {

    private static Integer uniqueId = 1;

    private UUID id;
    private String name;

    private HotelViewBuilder() {
        id(UUID.randomUUID());
        name("Hotel " + uniqueId);
        uniqueId++;
    }

    public static HotelViewBuilder hotelView() {
        return new HotelViewBuilder();
    }

    public HotelViewBuilder id(UUID id) {
        this.id = id;
        return this;
    }

    public HotelViewBuilder id(String id) {
        this.id = UUID.fromString(id);
        return this;
    }

    public HotelViewBuilder name(String name) {
        this.name = name;
        return this;
    }

    public HotelView build() {
        return new HotelView(id, name);
    }
}
