package hotel_management.hotel_manager.infrastructure.persistence.mongodb;

import hotel_management.hotel_manager.domain.Hotel;
import org.springframework.stereotype.Component;

@Component
public class MongoHotelMapper {

    public MongoHotel toMongo(Hotel hotel) {
        return new MongoHotel(
            hotel.id(),
            hotel.name()
        );
    }

    public Hotel toHotel(MongoHotel data) {
        return new Hotel(
            data.id(),
            data.name()
        );
    }

}
