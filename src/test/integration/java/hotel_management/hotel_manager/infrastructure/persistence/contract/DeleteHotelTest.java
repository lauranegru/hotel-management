package hotel_management.hotel_manager.infrastructure.persistence.contract;

import hotel_management.hotel_manager.domain.HotelRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static hotel_management.hotel_manager.domain.HotelGenerator.anyHotel;

public abstract class DeleteHotelTest {

    private HotelRepository repository;

    protected abstract HotelRepository repository();

    @BeforeEach
    public void setUp() {
        repository = repository();
    }

    @AfterEach
    void tearDown() {
        repository.delete().block();
    }

    @Test
    void does_not_throw_any_exception_when_no_hotels_exist() {
        var result = repository.delete();

        StepVerifier.create(result)
            .verifyComplete();
    }

    @Test
    void deletes_the_hotel_when_a_single_hotel_exists() {
        var hotel = anyHotel();

        var result = Mono.empty()
            .then(repository.save(hotel))
            .then(repository.delete())
            .then(repository.find(hotel.id()));

        StepVerifier.create(result)
            .verifyComplete();
    }

    @Test
    void deletes_all_hotels_when_multiple_hotels_exist() {
        var hotelOne = anyHotel();
        var hotelTwo = anyHotel();

        var result = Flux.concat(
            Flux.concat(
                repository.save(hotelOne),
                repository.save(hotelTwo),
                repository.delete()
            ),
            Flux.merge(
                repository.find(hotelOne.id()),
                repository.find(hotelTwo.id())
            )
        );

        StepVerifier.create(result)
            .verifyComplete();
    }

}
