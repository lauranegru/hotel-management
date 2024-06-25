package hotel_management.hotel_manager.infrastructure.persistence.mongodb;

import java.util.UUID;

public class HotelDataGenerator {

    private static Integer uniqueId = 1;

    private UUID id;
    private String name;

    private HotelDataGenerator() {
        id(UUID.randomUUID());
        name("Hotel " + uniqueId);
        uniqueId++;
    }

    public static HotelDataGenerator hotelData() {
        return new HotelDataGenerator();
    }

    public HotelDataGenerator id(UUID id) {
        this.id = id;
        return this;
    }

    public HotelDataGenerator id(String id) {
        this.id = UUID.fromString(id);
        return this;
    }

    public HotelDataGenerator name(String name) {
        this.name = name;
        return this;
    }

    public HotelData build() {
        return new HotelData(id, name);
    }

}
