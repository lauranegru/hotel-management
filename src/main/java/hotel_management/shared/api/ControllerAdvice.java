package hotel_management.shared.api;

import hotel_management.hotel_manager.domain.InvalidHotelName;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static hotel_management.shared.api.ErrorResponseBuilder.errorResponse;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class ControllerAdvice {

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(InvalidHotelName.class)
    public ErrorResponse handle(InvalidHotelName exception) {
        return errorResponse()
            .type("invalid-hotel-name")
            .message(exception.getMessage())
            .build();
    }

}
