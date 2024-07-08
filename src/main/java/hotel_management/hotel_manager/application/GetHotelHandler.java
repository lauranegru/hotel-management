package hotel_management.hotel_manager.application;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GetHotelHandler {

    public Optional<HotelView> execute(GetHotelQuery query) {
        return Optional.empty();
    }

}
