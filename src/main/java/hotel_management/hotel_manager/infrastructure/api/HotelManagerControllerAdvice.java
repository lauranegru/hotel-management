package hotel_management.hotel_manager.infrastructure.api;

import hotel_management.hotel_manager.domain.HotelAlreadyExists;
import hotel_management.hotel_manager.domain.HotelNotFound;
import hotel_management.hotel_manager.domain.InvalidHotelId;
import hotel_management.hotel_manager.domain.InvalidHotelName;
import hotel_management.shared.api.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static hotel_management.shared.api.ErrorResponseBuilder.errorResponse;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class HotelManagerControllerAdvice {

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

    @ResponseStatus(CONFLICT)
    @ExceptionHandler(HotelAlreadyExists.class)
    public ErrorResponse handle(HotelAlreadyExists exception) {
        return errorResponse()
            .type("hotel-already-exists")
            .message(exception.getMessage())
            .build();
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(HotelNotFound.class)
    public ErrorResponse handle(HotelNotFound exception) {
        return errorResponse()
            .type("hotel-not-found")
            .message(exception.getMessage())
            .build();
    }

}
