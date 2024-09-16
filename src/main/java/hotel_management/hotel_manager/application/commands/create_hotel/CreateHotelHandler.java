package hotel_management.hotel_manager.application.commands.create_hotel;

import hotel_management.hotel_manager.domain.Hotel;
import hotel_management.hotel_manager.domain.HotelAlreadyExists;
import hotel_management.hotel_manager.domain.HotelId;
import hotel_management.hotel_manager.domain.HotelName;
import hotel_management.hotel_manager.domain.HotelRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CreateHotelHandler {

    private final HotelRepository repository;

    public CreateHotelHandler(HotelRepository repository) {
        this.repository = repository;
    }

    public Mono<Void> execute(CreateHotelCommand command) {
        return Mono.just(command)
            .map(this::createHotel)
            .flatMap(this::assertUnique)
            .flatMap(repository::save);
    }

    private Hotel createHotel(CreateHotelCommand cmd) {
        return new Hotel(
            HotelId.of(cmd.id()),
            HotelName.of(cmd.name())
        );
    }

    private Mono<Hotel> assertUnique(Hotel hotel) {
        return repository.find(hotel.id())
            .doOnNext(h -> {throw new HotelAlreadyExists("The hotel with the given id already exists");})
            .thenReturn(hotel);
    }

}
