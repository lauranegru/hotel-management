package hotel_management.hotel_manager.infrastructure.persistence;

import hotel_management.hotel_manager.domain.Hotel;
import hotel_management.hotel_manager.domain.HotelRepository;
import org.springframework.stereotype.Component;

@Component
public class NullHotelRepository implements HotelRepository {

    @Override
    public void save(Hotel hotel) {
    }

}
