package hotel_management.hotel_manager.infrastructure.persistence.mongodb;

import hotel_management.hotel_manager.domain.Hotel;
import hotel_management.hotel_manager.domain.HotelRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;

@Component
public class MongoHotelRepository implements HotelRepository {

    private final MongoHotelDataRepository repository;
    private final HotelDataMapper mapper;

    public MongoHotelRepository(
        MongoHotelDataRepository repository,
        HotelDataMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Hotel> find(UUID id) {
        return repository.findById(id)
            .map(mapper::convert)
            .blockOptional();
    }

    @Override
    public void save(Hotel hotel) {
        Mono.just(hotel)
            .map(mapper::convert)
            .flatMap(repository::save)
            .block();
    }

    @Override
    public void delete() {
        repository.deleteAll().block();
    }

}
