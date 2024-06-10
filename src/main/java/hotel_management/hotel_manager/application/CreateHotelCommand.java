package hotel_management.hotel_manager.application;

import java.util.UUID;

public record CreateHotelCommand(
    UUID id,
    String name
) {
}
