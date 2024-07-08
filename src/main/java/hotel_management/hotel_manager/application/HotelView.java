package hotel_management.hotel_manager.application;

import java.util.UUID;

public record HotelView(
    UUID id,
    String name
) {
}
