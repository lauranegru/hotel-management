package hotel_management.shared.api.rest;

import org.springframework.http.HttpMethod;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

public class RestClient {

    private final WebTestClient client;

    public RestClient(WebTestClient client) {
        this.client = client;
    }

    public RestResponse send(RestRequest request) {
        var response = client
            .method(HttpMethod.valueOf(request.method()))
            .uri(request.path())
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON)
            .body(Mono.justOrEmpty(request.body()), String.class)
            .exchange()
            .returnResult(String.class);

        var status = response
            .getStatus()
            .value();

        var body = response
            .getResponseBody()
            .singleOrEmpty()
            .blockOptional();

        return new RestResponse(status, body);
    }

}
