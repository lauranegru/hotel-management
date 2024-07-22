package hotel_management.shared.api;

import hotel_management.shared.api.ErrorResponse.ServiceError;

public class ErrorResponseBuilder {

    private String type;
    private String message;

    private ErrorResponseBuilder() {
    }

    public static ErrorResponseBuilder errorResponse() {
        return new ErrorResponseBuilder();
    }

    public ErrorResponseBuilder type(String type) {
        this.type = type;
        return this;
    }

    public ErrorResponseBuilder message(String message) {
        this.message = message;
        return this;
    }

    public ErrorResponse build() {
        return new ErrorResponse(
            new ServiceError(type, message)
        );
    }

}
