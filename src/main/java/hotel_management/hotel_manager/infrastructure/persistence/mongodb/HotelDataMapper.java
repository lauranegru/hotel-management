package hotel_management.hotel_manager.infrastructure.persistence.mongodb;

import hotel_management.hotel_manager.domain.Hotel;
import org.springframework.stereotype.Component;

@Component
public class HotelDataMapper {

    public HotelData convert(Hotel hotel) {
        return new HotelData(
            hotel.id(),
            hotel.name()
        );
    }

    public Hotel convert(HotelData data) {
        return new Hotel(
            data.id(),
            data.name()
        );
    }

}
