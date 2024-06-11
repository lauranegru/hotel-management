package hotel_management.hotel_manager.application;

import hotel_management.hotel_manager.domain.Hotel;
import hotel_management.hotel_manager.domain.HotelRepository;
import org.springframework.stereotype.Component;

@Component
public class CreateHotelHandler {

    private final HotelRepository repository;

    public CreateHotelHandler(HotelRepository repository) {
        this.repository = repository;
    }

    public void execute(CreateHotelCommand command) {
        var hotel = new Hotel(command.id(), command.name());
        repository.save(hotel);
    }

}
