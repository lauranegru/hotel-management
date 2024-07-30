package hotel_management.hotel_manager.domain;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatException;

class HotelIdTest {

    @Test
    void accepts_valid_hotel_ids() {
        var value = UUID.randomUUID();
        var hotelId = HotelId.of(value);
        assertThat(hotelId.value()).isEqualTo(value);
    }

    @Test
    void rejects_null_hotel_ids() {
        assertThatException()
            .isThrownBy(() -> HotelId.of(null))
            .isInstanceOf(InvalidHotelId.class)
            .withMessage("The hotel id should not be null");
    }

}