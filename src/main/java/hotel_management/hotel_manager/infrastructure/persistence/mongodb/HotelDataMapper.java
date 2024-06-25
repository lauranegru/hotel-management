package hotel_management.hotel_manager.infrastructure.persistence.mongodb;

import hotel_management.hotel_manager.domain.Hotel;

public class HotelDataMapper {

    public HotelData convert(Hotel hotel) {
        return new HotelData(
            hotel.id(),
            hotel.name()
        );
    }

}
