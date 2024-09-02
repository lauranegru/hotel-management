package hotel_management.hotel_manager.application.commands.create_hotel;

import hotel_management.hotel_manager.domain.Hotel;
import hotel_management.hotel_manager.domain.HotelAlreadyExists;
import hotel_management.hotel_manager.domain.HotelId;
import hotel_management.hotel_manager.domain.HotelName;
import hotel_management.hotel_manager.domain.HotelRepository;
import org.springframework.stereotype.Component;

@Component
public class CreateHotelHandler {

    private final HotelRepository repository;

    public CreateHotelHandler(HotelRepository repository) {
        this.repository = repository;
    }

    public void execute(CreateHotelCommand command) {
        var hotel = new Hotel(
            HotelId.of(command.id()),
            HotelName.of(command.name())
        );

        var existing = repository
            .find(hotel.id())
            .blockOptional();

        if (existing.isPresent())
            throw new HotelAlreadyExists("The hotel with the given id already exists");

        repository.save(hotel);
    }

}
