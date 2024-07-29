package hotel_management.hotel_manager.application.commands.create_hotel;

import hotel_management.hotel_manager.domain.Hotel;
import hotel_management.hotel_manager.domain.HotelRepository;
import hotel_management.hotel_manager.domain.InvalidHotelId;
import hotel_management.hotel_manager.domain.InvalidHotelName;
import org.springframework.stereotype.Component;

@Component
public class CreateHotelHandler {

    private final HotelRepository repository;

    public CreateHotelHandler(HotelRepository repository) {
        this.repository = repository;
    }

    public void execute(CreateHotelCommand command) {
        if (command.id() == null)
            throw new InvalidHotelId("The hotel id should not be null");

        if (command.name() == null)
            throw new InvalidHotelName("The hotel name should not be null");

        var hotel = new Hotel(command.id(), command.name());

        repository.save(hotel);
    }

}
