package hotel_management.hotel_manager.application.commands.create_hotel;

import hotel_management.hotel_manager.domain.Hotel;
import hotel_management.hotel_manager.domain.HotelAlreadyExists;
import hotel_management.hotel_manager.domain.HotelId;
import hotel_management.hotel_manager.domain.HotelName;
import hotel_management.hotel_manager.domain.HotelRepository;
import reactor.core.publisher.Mono;

public class CreateHotelSteps {

    private final HotelRepository repository;

    public CreateHotelSteps(HotelRepository repository) {
        this.repository = repository;
    }

    public Hotel createHotel(CreateHotelCommand command) {
        return new Hotel(
            HotelId.of(command.id()),
            HotelName.of(command.name())
        );
    }

    public Mono<Hotel> assertUnique(Hotel hotel) {
        return repository.find(hotel.id())
            .doOnNext(h -> {throw new HotelAlreadyExists("The hotel with the given id already exists");})
            .thenReturn(hotel);
    }

    public Mono<Void> save(Hotel hotel) {
        return repository.save(hotel);
    }

}
