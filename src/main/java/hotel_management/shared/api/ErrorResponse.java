package hotel_management.shared.api;

public record ErrorResponse(
    ServiceError error
) {

    record ServiceError(
        String type,
        String message
    ) {
    }

}
