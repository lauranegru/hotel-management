package hotel_management.hotel_manager.infrastructure.persistence.mongodb;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static hotel_management.hotel_manager.domain.HotelGenerator.hotel;
import static hotel_management.hotel_manager.infrastructure.persistence.mongodb.MongoHotelGenerator.mongoHotel;
import static org.assertj.core.api.Assertions.assertThat;

class MongoHotelMapperTest {

    private MongoHotelMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new MongoHotelMapper();
    }

    @Test
    void converts_hotel_to_mongo_hotel() {
        var hotel = hotel()
            .id("63564297-1cb7-4542-8f4e-a40c0a4c79d6")
            .name("Hotel 1")
            .build();

        var result = mapper.toMongo(hotel);

        var expected = mongoHotel()
            .id("63564297-1cb7-4542-8f4e-a40c0a4c79d6")
            .name("Hotel 1")
            .build();

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void converts_mongo_hotel_to_hotel() {
        var mongoHotel = mongoHotel()
            .id("63564297-1cb7-4542-8f4e-a40c0a4c79d6")
            .name("Hotel 1")
            .build();

        var result = mapper.toHotel(mongoHotel);

        var expected = hotel()
            .id("63564297-1cb7-4542-8f4e-a40c0a4c79d6")
            .name("Hotel 1")
            .build();

        assertThat(result).isEqualTo(expected);
    }

}