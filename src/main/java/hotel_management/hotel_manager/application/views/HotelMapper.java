package hotel_management.hotel_manager.application.views;

import hotel_management.hotel_manager.domain.Hotel;
import org.springframework.stereotype.Component;

@Component
public class HotelMapper {

    public HotelView toView(Hotel hotel) {
        return new HotelView(
            hotel.id(),
            hotel.name()
        );
    }

}
