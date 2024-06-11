package hotel_management.hotel_manager.domain;

import java.util.UUID;

public record Hotel(
    UUID id,
    String name
) {
}
