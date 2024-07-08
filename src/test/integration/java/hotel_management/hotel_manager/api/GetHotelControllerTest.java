package hotel_management.hotel_manager.api;

import hotel_management.hotel_manager.application.GetHotelHandler;
import hotel_management.shared.api.rest.RestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Optional;

import static hotel_management.hotel_manager.api.GetHotelRequestGenerator.getHotelRequest;
import static hotel_management.hotel_manager.api.GetHotelResponseGenerator.getHotelResponse;
import static hotel_management.hotel_manager.application.GetHotelQueryGenerator.getHotelQuery;
import static hotel_management.hotel_manager.application.HotelViewGenerator.hotelView;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@AutoConfigureWebTestClient
class GetHotelControllerTest {

    private RestClient client;

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private GetHotelHandler handler;

    @BeforeEach
    void setUp() {
        client = new RestClient(webTestClient);
    }

    @Test
    void returns_the_hotel_when_the_hotel_exists() {
        var request = getHotelRequest()
            .id("685cd9b3-4788-49d1-a754-cd1130b795a4")
            .build();

        var query = getHotelQuery()
            .id("685cd9b3-4788-49d1-a754-cd1130b795a4")
            .build();

        var view = hotelView()
            .id("685cd9b3-4788-49d1-a754-cd1130b795a4")
            .name("The Mallorca Hotel")
            .build();

        given(handler.execute(query))
            .willReturn(Optional.of(view));

        var response = client.send(request);

        var expected = getHotelResponse()
            .status(200)
            .id("685cd9b3-4788-49d1-a754-cd1130b795a4")
            .name("The Mallorca Hotel")
            .build();

        assertThat(response).isEqualTo(expected);
    }

}