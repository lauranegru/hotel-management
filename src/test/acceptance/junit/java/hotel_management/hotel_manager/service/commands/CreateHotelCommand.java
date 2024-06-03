package hotel_management.hotel_manager.service.commands;

import java.util.UUID;

public record CreateHotelCommand(
    UUID id,
    String name
) {
}
