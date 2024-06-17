package hotel_management.hotel_manager.domain;

import java.util.Optional;
import java.util.UUID;

public interface HotelRepository {

    Optional<Hotel> find(UUID id);

    void save(Hotel hotel);

}
