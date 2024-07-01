package hotel_management.hotel_manager.infrastructure.persistence.contract;

import hotel_management.hotel_manager.domain.HotelRepository;
import hotel_management.hotel_manager.infrastructure.persistence.memory.MemoryHotelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static hotel_management.hotel_manager.domain.HotelGenerator.anyHotel;
import static org.assertj.core.api.Assertions.assertThat;

public class FindHotelTest {

    private HotelRepository repository;

    @BeforeEach
    public void setUp() {
        repository = new MemoryHotelRepository();
    }

    @Test
    void returns_the_hotel_when_the_hotel_exists() {
        var hotel = anyHotel();

        repository.save(hotel);

        var result = repository
            .find(hotel.id())
            .orElseThrow();

        assertThat(result).isEqualTo(hotel);
    }

    @Test
    void returns_no_hotel_when_the_hotel_does_not_exist() {
        var hotel = anyHotel();

        repository.save(anyHotel());

        var result = repository
            .find(hotel.id());

        assertThat(result).isEmpty();
    }

    @Test
    void returns_no_hotel_when_no_hotels_exist() {
        var hotel = anyHotel();

        var result = repository
            .find(hotel.id());

        assertThat(result).isEmpty();
    }

}
