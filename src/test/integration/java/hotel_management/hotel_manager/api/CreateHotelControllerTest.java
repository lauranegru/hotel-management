package hotel_management.hotel_manager.api;

import hotel_management.shared.api.rest.RestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static hotel_management.hotel_manager.api.CreateHotelRequestBuilder.createHotelRequest;
import static hotel_management.shared.api.rest.RestResponseBuilder.response;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureWebTestClient
class CreateHotelControllerTest {

    private RestClient client;

    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        client = new RestClient(webTestClient);
    }

    @Test
    void returns_a_success_response_when_the_request_is_valid() {
        var request = createHotelRequest()
            .id("685cd9b3-4788-49d1-a754-cd1130b795a4")
            .name("The Mallorca Hotel")
            .build();

        var response = client.send(request);

        var expected = response()
            .status(201)
            .build();

        assertThat(response).isEqualTo(expected);
    }

}