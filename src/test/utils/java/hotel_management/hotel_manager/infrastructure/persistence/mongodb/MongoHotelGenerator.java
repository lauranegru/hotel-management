package hotel_management.hotel_manager.infrastructure.persistence.mongodb;

import java.util.UUID;

import static hotel_management.hotel_manager.domain.HotelIdGenerator.anyHotelIdValue;
import static hotel_management.hotel_manager.domain.HotelNameGenerator.anyHotelNameValue;

public class MongoHotelGenerator {

    private UUID id;
    private String name;

    private MongoHotelGenerator() {
        id(anyHotelIdValue());
        name(anyHotelNameValue());
    }

    public static MongoHotelGenerator mongoHotel() {
        return new MongoHotelGenerator();
    }

    public MongoHotelGenerator id(UUID id) {
        this.id = id;
        return this;
    }

    public MongoHotelGenerator id(String id) {
        this.id = UUID.fromString(id);
        return this;
    }

    public MongoHotelGenerator name(String name) {
        this.name = name;
        return this;
    }

    public MongoHotel build() {
        return new MongoHotel(id, name);
    }

}
