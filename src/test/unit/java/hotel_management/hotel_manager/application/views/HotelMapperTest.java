package hotel_management.hotel_manager.application.views;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static hotel_management.hotel_manager.application.views.HotelViewGenerator.hotelView;
import static hotel_management.hotel_manager.domain.HotelGenerator.hotel;
import static org.assertj.core.api.Assertions.assertThat;

class HotelMapperTest {

    private HotelMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new HotelMapper();
    }

    @Test
    void converts_hotel_to_hotel_view() {
        var hotel = hotel()
            .id("685cd9b3-4788-49d1-a754-cd1130b795a4")
            .name("The Mallorca Hotel")
            .build();

        var result = mapper.toView(hotel);

        var view = hotelView()
            .id("685cd9b3-4788-49d1-a754-cd1130b795a4")
            .name("The Mallorca Hotel")
            .build();

        assertThat(result).isEqualTo(view);
    }

}