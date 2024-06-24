package hotel_management.hotel_manager.infrastructure.persistence.mongodb;

import hotel_management.hotel_manager.domain.Hotel;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import java.util.UUID;

public interface MongoHotelDataRepository extends ReactiveMongoRepository<Hotel, UUID> {
}
