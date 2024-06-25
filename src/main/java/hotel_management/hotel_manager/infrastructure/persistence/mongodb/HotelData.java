package hotel_management.hotel_manager.infrastructure.persistence.mongodb;

import java.util.UUID;

public record HotelData(
    UUID id,
    String name
) {
}
