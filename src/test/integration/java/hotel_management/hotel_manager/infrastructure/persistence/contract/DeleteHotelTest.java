package hotel_management.hotel_manager.infrastructure.persistence.contract;

import hotel_management.hotel_manager.domain.HotelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

}
