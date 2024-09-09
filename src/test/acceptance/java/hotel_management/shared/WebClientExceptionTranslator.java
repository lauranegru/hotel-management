package hotel_management.shared;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

public class WebClientExceptionTranslator {

    private final ObjectMapper mapper;

    public WebClientExceptionTranslator() {
        this.mapper = new ObjectMapper()
            .configure(FAIL_ON_UNKNOWN_PROPERTIES, true)
            .configure(FAIL_ON_MISSING_CREATOR_PROPERTIES, true);
    }

    public <T> Mono<T> translate(Mono<T> source) {
        return source.onErrorMap(WebClientResponseException.class, this::translate);
    }

    private Throwable translate(WebClientResponseException exception) {
        try {
            var response = exception.getResponseBodyAsString();
            var errorResponse = mapper.readValue(response, ErrorResponse.class);

            return new ServiceException(
                errorResponse.error().type(),
                errorResponse.error().message()
            );

        } catch (Exception e) {
            return exception;
        }
    }

}
