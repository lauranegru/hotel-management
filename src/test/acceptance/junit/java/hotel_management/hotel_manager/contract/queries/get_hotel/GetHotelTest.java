package hotel_management.hotel_manager.contract.queries.get_hotel;

import hotel_management.hotel_manager.domain.HotelRepository;
import hotel_management.hotel_manager.service.HotelService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static hotel_management.hotel_manager.contract.commands.create_hotel.CreateHotelCommandGenerator.createHotelCommand;
import static hotel_management.hotel_manager.contract.queries.get_hotel.GetHotelQueryGenerator.getHotelQuery;
import static hotel_management.hotel_manager.contract.views.HotelViewGenerator.hotelView;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@SpringBootTest(webEnvironment = DEFINED_PORT)
public class GetHotelTest {

    @Autowired
    private HotelService service;

    @Autowired
    private HotelRepository repository;

    @AfterEach
    void tearDown() {
        repository.delete();
    }

    @Test
    void returns_the_hotel_when_the_hotel_exists() {
        var command = createHotelCommand()
            .id("46f75501-563d-4bc0-8922-2ea8dc0e1d67")
            .name("Hotel One")
            .build();

        service.execute(command);

        var query = getHotelQuery()
            .id("46f75501-563d-4bc0-8922-2ea8dc0e1d67")
            .build();

        var hotel = service.execute(query);

        var expected = hotelView()
            .id("46f75501-563d-4bc0-8922-2ea8dc0e1d67")
            .name("Hotel One")
            .build();

        assertThat(hotel).isEqualTo(expected);
    }

}
