package hotel_management.hotel_manager.infrastructure.persistence.mongodb;

import hotel_management.hotel_manager.domain.Hotel;
import hotel_management.hotel_manager.domain.HotelRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class MongoHotelRepository implements HotelRepository {

    private final MongoHotelDataRepository repository;

    public MongoHotelRepository(MongoHotelDataRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Hotel> find(UUID id) {
        return repository.findById(id)
            .blockOptional();
    }

    @Override
    public void save(Hotel hotel) {
        repository.save(hotel).block();
    }

    @Override
    public void delete() {
        repository.deleteAll().block();
    }

}
