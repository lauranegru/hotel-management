package hotel_management.hotel_manager.infrastructure.persistence;

import hotel_management.hotel_manager.domain.Hotel;
import hotel_management.hotel_manager.domain.HotelRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Component
public class MemoryHotelRepository implements HotelRepository {

    private final Map<UUID, Hotel> hotels;

    public MemoryHotelRepository() {
        hotels = new HashMap<>();
    }

    @Override
    public Optional<Hotel> find(UUID id) {
        return Optional.of(hotels.get(id));
    }

    @Override
    public void save(Hotel hotel) {
        hotels.put(hotel.id(), hotel);
    }

}
