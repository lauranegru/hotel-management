package hotel_management.shared.api;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebInputException;

import static hotel_management.shared.api.ErrorResponseBuilder.errorResponse;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class SharedControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(SharedControllerAdvice.class);

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

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(WebExchangeBindException.class)
    public ErrorResponse handle(WebExchangeBindException exception) {
        var field = exception.getFieldError().getField();
        var type = exception.getFieldType(field).getSimpleName();
        var message = "The field " + field + " should have " + type + " type";

        return errorResponse()
            .type("validation-error")
            .message(message)
            .build();
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handle(Exception exception) {
        logger.error(exception.getMessage(), exception);
        return errorResponse()
            .type("unexpected-error")
            .message("Unexpected error")
            .build();
    }

}
