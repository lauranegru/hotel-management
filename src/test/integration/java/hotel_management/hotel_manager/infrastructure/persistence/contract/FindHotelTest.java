package hotel_management.hotel_manager.infrastructure.persistence.contract;

import hotel_management.hotel_manager.domain.HotelRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static hotel_management.hotel_manager.domain.HotelGenerator.anyHotel;

public abstract class FindHotelTest {

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
    void returns_the_hotel_when_the_hotel_exists() {
        var hotel = anyHotel();

        var result = Mono
            .when(
                repository.save(hotel),
                repository.save(anyHotel()),
                repository.save(anyHotel())
            )
            .then(
                repository.find(hotel.id())
            );

        StepVerifier.create(result)
            .expectNext(hotel)
            .verifyComplete();
    }

    @Test
    void returns_no_hotel_when_the_hotel_does_not_exist() {
        var hotel = anyHotel();

        var result = Mono.empty()
            .then(repository.save(anyHotel()))
            .then(repository.save(anyHotel()))
            .then(repository.save(anyHotel()))
            .then(repository.find(hotel.id()));

        StepVerifier.create(result)
            .verifyComplete();
    }

    @Test
    void returns_no_hotel_when_no_hotels_exist() {
        var hotel = anyHotel();

        var result = repository.find(hotel.id());

        StepVerifier.create(result)
            .verifyComplete();
    }

}
