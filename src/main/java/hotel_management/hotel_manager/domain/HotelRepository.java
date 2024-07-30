package hotel_management.hotel_manager.domain;

import java.util.Optional;

public interface HotelRepository {

    Optional<Hotel> find(HotelId id);

    void save(Hotel hotel);

    void delete();

}
