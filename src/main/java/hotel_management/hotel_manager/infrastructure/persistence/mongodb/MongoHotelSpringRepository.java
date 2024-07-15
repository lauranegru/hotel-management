package hotel_management.hotel_manager.infrastructure.persistence.mongodb;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import java.util.UUID;

public interface MongoHotelSpringRepository extends ReactiveMongoRepository<MongoHotel, UUID> {
}
