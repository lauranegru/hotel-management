package hotel_management.hotel_manager.infrastructure.persistence.mongodb;

import hotel_management.hotel_manager.domain.Hotel;
import hotel_management.hotel_manager.domain.HotelId;
import hotel_management.hotel_manager.domain.HotelName;
import org.springframework.stereotype.Component;

@Component
public class MongoHotelMapper {

    public MongoHotel toMongo(Hotel hotel) {
        return new MongoHotel(
            hotel.id().value(),
            hotel.name().value()
        );
    }

    public Hotel toHotel(MongoHotel data) {
        return new Hotel(
            HotelId.of(data.id()),
            HotelName.of(data.name())
        );
    }

}
