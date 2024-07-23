package hotel_management.shared.api;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import hotel_management.hotel_manager.domain.InvalidHotelId;
import hotel_management.hotel_manager.domain.InvalidHotelName;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebInputException;

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

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(InvalidHotelId.class)
    public ErrorResponse handle(InvalidHotelId exception) {
        return errorResponse()
            .type("invalid-hotel-id")
            .message(exception.getMessage())
            .build();
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(ServerWebInputException.class)
    public ErrorResponse handle(MismatchedInputException exception) {
        var field = exception.getPathReference().split("\"")[1];
        var type = exception.getTargetType().getSimpleName();
        var message = "The field " + field + " should have " + type + " type";

        return errorResponse()
            .type("validation-error")
            .message(message)
            .build();
    }

}
