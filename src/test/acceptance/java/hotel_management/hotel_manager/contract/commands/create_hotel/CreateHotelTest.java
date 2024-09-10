package hotel_management.hotel_manager.contract.commands.create_hotel;

import hotel_management.hotel_manager.domain.HotelRepository;
import hotel_management.hotel_manager.service.HotelService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static hotel_management.hotel_manager.contract.commands.create_hotel.CreateHotelCommandGenerator.createHotelCommand;
import static hotel_management.hotel_manager.contract.queries.get_hotel.GetHotelQueryGenerator.getHotelQuery;
import static hotel_management.hotel_manager.contract.views.HotelViewGenerator.hotelView;
import static hotel_management.shared.ServiceExceptionGenerator.serviceException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchException;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@SpringBootTest(webEnvironment = DEFINED_PORT)
public class CreateHotelTest {

    @Autowired
    private HotelService service;

    @Autowired
    private HotelRepository repository;

    @AfterEach
    void tearDown() {
        repository.delete().block();
    }

    @Test
    void creates_the_hotel_when_the_command_is_valid() {
        var createHotel = createHotelCommand()
            .id("685cd9b3-4788-49d1-a754-cd1130b795a4")
            .name("The Mallorca Hotel")
            .build();

        service.execute(createHotel);

        var getHotel = getHotelQuery()
            .id("685cd9b3-4788-49d1-a754-cd1130b795a4")
            .build();

        var hotel = service.execute(getHotel);

        var expected = hotelView()
            .id("685cd9b3-4788-49d1-a754-cd1130b795a4")
            .name("The Mallorca Hotel")
            .build();

        assertThat(hotel).isEqualTo(expected);
    }

    @Test
    void throws_an_exception_when_the_command_is_invalid() {
        var createHotel = createHotelCommand()
            .blankName()
            .build();

        var exception = catchException(
            () -> service.execute(createHotel)
        );

        var expected = serviceException()
            .type("invalid-hotel-name")
            .message("The hotel name should not be blank")
            .build();

        assertThat(exception).isEqualTo(expected);
    }

    @Test
    void throws_an_exception_when_the_hotel_already_exists() {
        var createHotelOne = createHotelCommand()
            .id("685cd9b3-4788-49d1-a754-cd1130b795a4")
            .name("Hotel 1")
            .build();

        var createHotelTwo = createHotelCommand()
            .id("685cd9b3-4788-49d1-a754-cd1130b795a4")
            .name("Hotel 2")
            .build();

        service.execute(createHotelOne);

        var exception = catchException(
            () -> service.execute(createHotelTwo)
        );

        var expected = serviceException()
            .type("hotel-already-exists")
            .message("The hotel with the given id already exists")
            .build();

        assertThat(exception).isEqualTo(expected);
    }

}
