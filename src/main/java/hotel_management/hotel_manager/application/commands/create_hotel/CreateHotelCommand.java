package hotel_management.hotel_manager.application.commands.create_hotel;

import java.util.UUID;

public record CreateHotelCommand(
    UUID id,
    String name
) {
}
