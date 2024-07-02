package hotel_management.hotel_manager.infrastructure.persistence.contract;

import hotel_management.hotel_manager.domain.HotelGenerator;
import hotel_management.hotel_manager.domain.HotelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static hotel_management.hotel_manager.domain.HotelGenerator.anyHotel;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

public abstract class DeleteHotelTest {

    private HotelRepository repository;

    protected abstract HotelRepository repository();

    @BeforeEach
    public void setUp() {
        repository = repository();
    }

    @Test
    void deletes_no_hotels_when_no_hotels_exist() {
        assertThatNoException()
            .isThrownBy(repository::delete);
    }

    @Test
    void deletes_the_hotel_when_a_single_hotel_exists() {
        var hotel = anyHotel();

        repository.save(hotel);
        repository.delete();

        var result = repository.find(hotel.id());

        assertThat(result).isEmpty();
    }

}
