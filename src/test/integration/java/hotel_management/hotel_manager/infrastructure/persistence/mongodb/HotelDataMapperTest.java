package hotel_management.hotel_manager.infrastructure.persistence.mongodb;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static hotel_management.hotel_manager.domain.HotelGenerator.hotel;
import static hotel_management.hotel_manager.infrastructure.persistence.mongodb.HotelDataGenerator.hotelData;
import static org.assertj.core.api.Assertions.assertThat;

class HotelDataMapperTest {

    private HotelDataMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new HotelDataMapper();
    }

    @Test
    void converts_hotel_to_hotel_data() {
        var hotel = hotel()
            .id("63564297-1cb7-4542-8f4e-a40c0a4c79d6")
            .name("Hotel 1")
            .build();

        var result = mapper.convert(hotel);

        var expected = hotelData()
            .id("63564297-1cb7-4542-8f4e-a40c0a4c79d6")
            .name("Hotel 1")
            .build();

        assertThat(result).isEqualTo(expected);
    }

}