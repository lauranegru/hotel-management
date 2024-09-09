package hotel_management.shared;

record ErrorResponse(
    ServiceError error
) {
    record ServiceError(
        String type,
        String message
    ) {
    }
}
