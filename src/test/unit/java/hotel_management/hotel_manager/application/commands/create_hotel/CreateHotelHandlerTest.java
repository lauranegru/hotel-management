package hotel_management.hotel_manager.application.commands.create_hotel;

import hotel_management.hotel_manager.domain.HotelAlreadyExists;
import hotel_management.hotel_manager.domain.HotelId;
import hotel_management.hotel_manager.domain.HotelRepository;
import hotel_management.hotel_manager.domain.InvalidHotelId;
import hotel_management.hotel_manager.domain.InvalidHotelName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import static hotel_management.hotel_manager.application.commands.create_hotel.CreateHotelCommandGenerator.createHotelCommand;
import static hotel_management.hotel_manager.domain.HotelGenerator.hotel;
import static org.assertj.core.api.Assertions.assertThatException;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CreateHotelHandlerTest {

    private CreateHotelHandler handler;

    @Mock
    private HotelRepository repository;

    @BeforeEach
    void setUp() {
        handler = new CreateHotelHandler(repository);
    }

    @Test
    void creates_the_hotel_when_the_command_is_valid() {
        var command = createHotelCommand()
            .id("685cd9b3-4788-49d1-a754-cd1130b795a4")
            .name("The Mallorca Hotel")
            .build();

        given(repository
            .find(HotelId.of(command.id())))
            .willReturn(Mono.empty());

        var hotel = hotel()
            .id("685cd9b3-4788-49d1-a754-cd1130b795a4")
            .name("The Mallorca Hotel")
            .build();

        given(repository
            .save(hotel))
            .willReturn(Mono.empty());

        handler.execute(command);

        verify(repository).save(hotel);
    }

    @Test
    void throws_an_exception_when_the_hotel_already_exists() {
        var command = createHotelCommand()
            .id("685cd9b3-4788-49d1-a754-cd1130b795a4")
            .build();

        var hotel = hotel()
            .id("685cd9b3-4788-49d1-a754-cd1130b795a4")
            .build();

        given(repository
            .find(hotel.id()))
            .willReturn(Mono.just(hotel));

        assertThatException()
            .isThrownBy(() -> handler.execute(command))
            .isInstanceOf(HotelAlreadyExists.class)
            .withMessage("The hotel with the given id already exists");
    }

    @Test
    void throws_an_exception_when_the_hotel_name_is_invalid() {
        var command = createHotelCommand()
            .invalidName()
            .build();

        assertThatException()
            .isThrownBy(() -> handler.execute(command))
            .isInstanceOf(InvalidHotelName.class);
    }

    @Test
    void throws_an_exception_when_the_hotel_id_is_invalid() {
        var command = createHotelCommand()
            .invalidId()
            .build();

        assertThatException()
            .isThrownBy(() -> handler.execute(command))
            .isInstanceOf(InvalidHotelId.class);
    }

}