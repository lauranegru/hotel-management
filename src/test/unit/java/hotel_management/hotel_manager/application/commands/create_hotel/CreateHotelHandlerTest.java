package hotel_management.hotel_manager.application.commands.create_hotel;

import hotel_management.hotel_manager.domain.HotelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static hotel_management.hotel_manager.application.commands.create_hotel.CreateHotelCommandGenerator.createHotelCommand;
import static hotel_management.hotel_manager.domain.HotelGenerator.hotel;
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

        handler.execute(command);

        var hotel = hotel()
            .id("685cd9b3-4788-49d1-a754-cd1130b795a4")
            .name("The Mallorca Hotel")
            .build();

        verify(repository).save(hotel);
    }

}