package hotel_management.hotel_manager.contract.views;

import hotel_management.hotel_manager.service.views.HotelView;

import java.util.UUID;

import static hotel_management.hotel_manager.domain.HotelIdGenerator.anyHotelIdValue;
import static hotel_management.hotel_manager.domain.HotelNameGenerator.anyHotelNameValue;

public class HotelViewGenerator {

    private UUID id;
    private String name;

    private HotelViewGenerator() {
        id(anyHotelIdValue());
        name(anyHotelNameValue());
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
