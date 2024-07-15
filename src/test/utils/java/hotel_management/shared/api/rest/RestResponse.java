package hotel_management.shared.api.rest;

import java.util.Optional;

public record RestResponse(
    Integer status,
    Optional<String> body
) {
}
