package hotel_management.hotel_manager.service.commands.create_hotel;

import java.util.UUID;

public record CreateHotelCommand(
    UUID id,
    String name
) {
}
