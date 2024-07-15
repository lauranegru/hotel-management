package hotel_management.shared.api.rest;

import java.util.Optional;

public record RestRequest(
    String method,
    String path,
    Optional<String> body
) {
}
