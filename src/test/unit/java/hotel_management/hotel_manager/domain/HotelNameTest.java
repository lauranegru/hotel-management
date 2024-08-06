package hotel_management.hotel_manager.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatException;

class HotelNameTest {

    public static final int MAXIMUM_LENGTH = 25;

    @Test
    void accepts_valid_hotel_names() {
        var value = "Hotel 1";
        var hotelName = HotelName.of(value);
        assertThat(hotelName.value()).isEqualTo(value);
    }

    @Test
    void accepts_hotel_names_with_maximum_length() {
        var value = "X".repeat(MAXIMUM_LENGTH);
        var hotelName = HotelName.of(value);
        assertThat(hotelName.value()).isEqualTo(value);
    }

    @Test
    void rejects_hotel_names_over_the_maximum_length() {
        assertThatException()
            .isThrownBy(() -> HotelName.of("X".repeat(MAXIMUM_LENGTH + 1)))
            .isInstanceOf(InvalidHotelName.class)
            .withMessage("The hotel name should have a maximum of 25 characters");
    }

    @Test
    void rejects_null_hotel_names() {
        assertThatException()
            .isThrownBy(() -> HotelName.of(null))
            .isInstanceOf(InvalidHotelName.class)
            .withMessage("The hotel name should not be missing");
    }

    @Test
    void rejects_empty_hotel_names() {
        assertThatException()
            .isThrownBy(() -> HotelName.of(""))
            .isInstanceOf(InvalidHotelName.class)
            .withMessage("The hotel name should not be blank");
    }

    @Test
    void rejects_blank_hotel_names() {
        assertThatException()
            .isThrownBy(() -> HotelName.of(" "))
            .isInstanceOf(InvalidHotelName.class)
            .withMessage("The hotel name should not be blank");
    }

}