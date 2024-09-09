package hotel_management.hotel_manager.infrastructure.persistence.contract;

import hotel_management.hotel_manager.domain.HotelRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static hotel_management.hotel_manager.domain.HotelGenerator.anyHotel;
import static hotel_management.hotel_manager.domain.HotelGenerator.hotel;

public abstract class SaveHotelTest {

    private HotelRepository repository;

    protected abstract HotelRepository repository();

    @BeforeEach
    void setUp() {
        repository = repository();
    }

    @AfterEach
    void tearDown() {
        repository.delete();
    }

    @Test
    void creates_the_hotel_when_the_hotel_does_not_exist() {
        var hotel = anyHotel();

        repository.save(hotel).block();

        var result = repository.find(hotel.id());

        StepVerifier.create(result)
            .expectNext(hotel)
            .verifyComplete();
    }

    @Test
    void updates_the_hotel_when_the_hotel_already_exists() {
        var existing = anyHotel();

        var updated = hotel()
            .id(existing.id())
            .build();

        repository.save(existing).block();
        repository.save(updated).block();

        var result = repository.find(existing.id());

        StepVerifier.create(result)
            .expectNext(updated)
            .verifyComplete();
    }

    @Test
    void does_not_update_existing_hotels_with_other_ids() {
        var existing = anyHotel();
        var target = anyHotel();

        var updated = hotel()
            .id(target.id())
            .build();

        repository.save(existing).block();
        repository.save(target).block();
        repository.save(updated).block();

        var result = repository.find(existing.id());

        StepVerifier.create(result)
            .expectNext(existing)
            .verifyComplete();
    }

}