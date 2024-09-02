package hotel_management.hotel_manager.application.queries.get_hotel;

import hotel_management.hotel_manager.application.views.HotelMapper;
import hotel_management.hotel_manager.domain.HotelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import java.util.Optional;

import static hotel_management.hotel_manager.application.queries.get_hotel.GetHotelQueryGenerator.anyGetHotelQuery;
import static hotel_management.hotel_manager.application.queries.get_hotel.GetHotelQueryGenerator.getHotelQuery;
import static hotel_management.hotel_manager.application.views.HotelViewGenerator.hotelView;
import static hotel_management.hotel_manager.domain.HotelGenerator.hotel;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetHotelHandlerTest {

    private GetHotelHandler handler;

    @Mock
    private HotelRepository repository;

    @BeforeEach
    void setUp() {
        handler = new GetHotelHandler(repository, new HotelMapper());
    }

    @Test
    void returns_the_hotel_when_the_hotel_exists() {
        var query = getHotelQuery()
            .id("685cd9b3-4788-49d1-a754-cd1130b795a4")
            .build();

        var hotel = hotel()
            .id("685cd9b3-4788-49d1-a754-cd1130b795a4")
            .name("The Mallorca Hotel")
            .build();

        var view = hotelView()
            .id("685cd9b3-4788-49d1-a754-cd1130b795a4")
            .name("The Mallorca Hotel")
            .build();

        when(repository
            .find(hotel.id()))
            .thenReturn(Mono.just(hotel));

        var result = handler.execute(query);

        assertThat(result).contains(view);
    }

    @Test
    void returns_no_hotel_when_the_hotel_does_not_exist() {
        var query = anyGetHotelQuery();

        when(repository
            .find(any()))
            .thenReturn(Mono.empty());

        var result = handler.execute(query);

        assertThat(result).isEmpty();
    }

}