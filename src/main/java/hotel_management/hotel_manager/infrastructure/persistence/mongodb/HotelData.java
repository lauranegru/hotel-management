package hotel_management.hotel_manager.infrastructure.persistence.mongodb;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document("hotels")
public record HotelData(
    UUID id,
    String name
) {
}
