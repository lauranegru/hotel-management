package hotel_management.hotel_manager.api.queries.get_hotel;

import hotel_management.hotel_manager.application.queries.get_hotel.GetHotelHandler;
import hotel_management.hotel_manager.application.queries.get_hotel.GetHotelQuery;
import hotel_management.hotel_manager.domain.InvalidHotelId;
import hotel_management.shared.api.rest.RestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static hotel_management.hotel_manager.api.queries.get_hotel.GetHotelRequestGenerator.anyGetHotelRequest;
import static hotel_management.hotel_manager.api.queries.get_hotel.GetHotelRequestGenerator.getHotelRequest;
import static hotel_management.hotel_manager.api.queries.get_hotel.GetHotelResponseGenerator.getHotelResponse;
import static hotel_management.hotel_manager.application.queries.get_hotel.GetHotelQueryGenerator.getHotelQuery;
import static hotel_management.hotel_manager.application.views.HotelViewGenerator.hotelView;
import static hotel_management.shared.api.rest.RestErrorGenerator.errorResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

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
    void sends_a_get_hotel_query_when_the_request_is_valid() {
        var request = getHotelRequest()
            .id("685cd9b3-4788-49d1-a754-cd1130b795a4")
            .build();

        client.send(request);

        var query = getHotelQuery()
            .id("685cd9b3-4788-49d1-a754-cd1130b795a4")
            .build();

        verify(handler).execute(query);
    }

    @Test
    void returns_the_hotel_when_the_hotel_exists() {
        var view = hotelView()
            .id("685cd9b3-4788-49d1-a754-cd1130b795a4")
            .name("The Mallorca Hotel")
            .build();

        given(handler
            .execute(any(GetHotelQuery.class)))
            .willReturn(Mono.just(view));

        var response = client.send(anyGetHotelRequest());

        var expected = getHotelResponse()
            .status(200)
            .id("685cd9b3-4788-49d1-a754-cd1130b795a4")
            .name("The Mallorca Hotel")
            .build();

        assertThat(response).isEqualTo(expected);
    }

    @Test
    void returns_an_error_response_when_the_hotel_does_not_exist() {
        given(handler
            .execute(any(GetHotelQuery.class)))
            .willReturn(Mono.empty());

        var response = client.send(anyGetHotelRequest());

        var expected = errorResponse()
            .message("The hotel with the given id does not exist")
            .type("hotel-not-found")
            .status(404)
            .build();

        assertThat(response).isEqualTo(expected);
    }

    @Test
    void returns_an_error_response_when_the_id_is_missing() {
        givenException(new InvalidHotelId("The hotel id should not be missing"));

        var request = getHotelRequest()
            .missingId()
            .build();

        var response = client.send(request);

        var expected = errorResponse()
            .message("The hotel id should not be missing")
            .type("invalid-hotel-id")
            .status(400)
            .build();

        assertThat(response).isEqualTo(expected);
    }

    private void givenException(Throwable exception) {
        given(handler
            .execute(any(GetHotelQuery.class)))
            .willThrow(exception);
    }

}