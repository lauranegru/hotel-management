package hotel_management.hotel_manager.infrastructure.persistence.contract;

import hotel_management.hotel_manager.domain.HotelRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static hotel_management.hotel_manager.domain.HotelGenerator.anyHotel;
import static org.assertj.core.api.Assertions.assertThatNoException;

public abstract class DeleteHotelTest {

    private HotelRepository repository;

    protected abstract HotelRepository repository();

    @BeforeEach
    public void setUp() {
        repository = repository();
    }

    @AfterEach
    void tearDown() {
        repository.delete();
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

        StepVerifier.create(result)
            .verifyComplete();
    }

    @Test
    void deletes_all_hotels_when_multiple_hotels_exist() {
        var hotelOne = anyHotel();
        var hotelTwo = anyHotel();

        repository.save(hotelOne);
        repository.save(hotelTwo);

        repository.delete();

        var resultOne = repository.find(hotelOne.id());
        var resultTwo = repository.find(hotelTwo.id());

        StepVerifier.create(resultOne).verifyComplete();
        StepVerifier.create(resultTwo).verifyComplete();
    }

}
