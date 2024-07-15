package hotel_management.hotel_manager.infrastructure.persistence.mongodb;

import java.util.UUID;

public class MongoHotelGenerator {

    private static Integer uniqueId = 1;

    private UUID id;
    private String name;

    private MongoHotelGenerator() {
        id(UUID.randomUUID());
        name("Hotel " + uniqueId);
        uniqueId++;
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
