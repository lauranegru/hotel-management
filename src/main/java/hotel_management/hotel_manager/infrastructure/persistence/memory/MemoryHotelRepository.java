package hotel_management.hotel_manager.infrastructure.persistence.memory;

import hotel_management.hotel_manager.domain.Hotel;
import hotel_management.hotel_manager.domain.HotelId;
import hotel_management.hotel_manager.domain.HotelRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Primary
@Component
public class MemoryHotelRepository implements HotelRepository {

    private final Map<HotelId, Hotel> hotels;

    public MemoryHotelRepository() {
        hotels = new HashMap<>();
    }

    @Override
    public Mono<Hotel> find(HotelId id) {
        return Mono.fromSupplier(() -> hotels.get(id));
    }

    @Override
    public void save(Hotel hotel) {
        hotels.put(hotel.id(), hotel);
    }

    @Override
    public void delete() {
        hotels.clear();
    }

}
