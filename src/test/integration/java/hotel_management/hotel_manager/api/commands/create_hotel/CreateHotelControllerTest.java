package hotel_management.hotel_manager.api.commands.create_hotel;

import hotel_management.hotel_manager.application.commands.create_hotel.CreateHotelCommand;
import hotel_management.hotel_manager.application.commands.create_hotel.CreateHotelHandler;
import hotel_management.hotel_manager.domain.InvalidHotelId;
import hotel_management.hotel_manager.domain.InvalidHotelName;
import hotel_management.shared.api.rest.RestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import static hotel_management.hotel_manager.api.commands.create_hotel.CreateHotelRequestGenerator.anyCreateHotelRequest;
import static hotel_management.hotel_manager.api.commands.create_hotel.CreateHotelRequestGenerator.createHotelRequest;
import static hotel_management.hotel_manager.application.commands.create_hotel.CreateHotelCommandGenerator.createHotelCommand;
import static hotel_management.shared.api.rest.RestErrorGenerator.errorResponse;
import static hotel_management.shared.api.rest.RestResponseGenerator.response;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.verify;

@SpringBootTest
@AutoConfigureWebTestClient
class CreateHotelControllerTest {

    private RestClient client;

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private CreateHotelHandler handler;

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

    @Test
    void sends_a_create_hotel_command_when_the_request_is_valid() {
        var request = createHotelRequest()
            .id("685cd9b3-4788-49d1-a754-cd1130b795a4")
            .name("The Mallorca Hotel")
            .build();

        client.send(request);

        var command = createHotelCommand()
            .id("685cd9b3-4788-49d1-a754-cd1130b795a4")
            .name("The Mallorca Hotel")
            .build();

        verify(handler).execute(command);
    }

    @Test
    void returns_an_error_response_when_the_id_is_missing() {
        givenException(new InvalidHotelId("The hotel id should not be missing"));

        var request = createHotelRequest()
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

    @Test
    void returns_an_error_response_when_the_id_has_invalid_type() {
        var request = createHotelRequest()
            .invalidIdType()
            .build();

        var response = client.send(request);

        var expected = errorResponse()
            .message("The field id should have UUID type")
            .type("validation-error")
            .status(400)
            .build();

        assertThat(response).isEqualTo(expected);
    }

    @Test
    void returns_an_error_response_when_the_name_is_missing() {
        givenException(new InvalidHotelName("The hotel name should not be missing"));

        var request = createHotelRequest()
            .missingName()
            .build();

        var response = client.send(request);

        var expected = errorResponse()
            .message("The hotel name should not be missing")
            .type("invalid-hotel-name")
            .status(400)
            .build();

        assertThat(response).isEqualTo(expected);
    }

    @Test
    void returns_an_error_response_when_the_name_has_invalid_value() {
        givenException(new InvalidHotelName("The hotel name should not be blank"));

        var request = createHotelRequest()
            .invalidNameValue()
            .build();

        var response = client.send(request);

        var expected = errorResponse()
            .message("The hotel name should not be blank")
            .type("invalid-hotel-name")
            .status(400)
            .build();

        assertThat(response).isEqualTo(expected);
    }

    @Test
    void returns_an_error_response_when_the_name_has_invalid_type() {
        var request = createHotelRequest()
            .invalidNameType()
            .build();

        var response = client.send(request);

        var expected = errorResponse()
            .message("The field name should have String type")
            .type("validation-error")
            .status(400)
            .build();

        assertThat(response).isEqualTo(expected);
    }

    @Test
    void returns_an_error_response_when_an_unexpected_error_occurs() {
        givenException(new RuntimeException("An unexpected error occurred"));

        var request = anyCreateHotelRequest();

        var response = client.send(request);

        var expected = errorResponse()
            .message("Unexpected error")
            .type("unexpected-error")
            .status(500)
            .build();

        assertThat(response).isEqualTo(expected);
    }

    private void givenException(Throwable exception) {
        willThrow(exception).given(handler).execute(any(CreateHotelCommand.class));
    }

}