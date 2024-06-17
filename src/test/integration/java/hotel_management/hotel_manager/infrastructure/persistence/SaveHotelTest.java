package hotel_management.hotel_manager.infrastructure.persistence;

import hotel_management.hotel_manager.domain.HotelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static hotel_management.hotel_manager.domain.HotelGenerator.anyHotel;
import static hotel_management.hotel_manager.domain.HotelGenerator.hotel;
import static org.assertj.core.api.Assertions.assertThat;

class SaveHotelTest {

    private HotelRepository repository;

    @BeforeEach
    void setUp() {
        repository = new MemoryHotelRepository();
    }

    @Test
    void creates_the_hotel_when_the_hotel_does_not_exist() {
        var hotel = anyHotel();

        repository.save(hotel);

        var result = repository
            .find(hotel.id())
            .orElseThrow();

        assertThat(result).isEqualTo(hotel);
    }

    @Test
    void updates_the_hotel_when_the_hotel_already_exists() {
        var existing = hotel()
            .build();

        var updated = hotel()
            .id(existing.id())
            .build();

        repository.save(existing);
        repository.save(updated);

        var result = repository
            .find(existing.id())
            .orElseThrow();

        assertThat(result).isEqualTo(updated);
    }

}