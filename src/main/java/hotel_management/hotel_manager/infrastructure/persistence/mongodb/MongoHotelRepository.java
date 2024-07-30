package hotel_management.hotel_manager.infrastructure.persistence.mongodb;

import hotel_management.hotel_manager.domain.Hotel;
import hotel_management.hotel_manager.domain.HotelId;
import hotel_management.hotel_manager.domain.HotelRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
public class MongoHotelRepository implements HotelRepository {

    private final MongoHotelSpringRepository repository;
    private final MongoHotelMapper mapper;

    public MongoHotelRepository(
        MongoHotelSpringRepository repository,
        MongoHotelMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Hotel> find(HotelId id) {
        return repository.findById(id.value())
            .map(mapper::toHotel)
            .blockOptional();
    }

    @Override
    public void save(Hotel hotel) {
        Mono.just(hotel)
            .map(mapper::toMongo)
            .flatMap(repository::save)
            .block();
    }

    @Override
    public void delete() {
        repository.deleteAll().block();
    }

}
