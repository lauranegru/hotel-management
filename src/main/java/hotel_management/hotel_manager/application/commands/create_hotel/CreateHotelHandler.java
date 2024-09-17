package hotel_management.hotel_manager.application.commands.create_hotel;

import hotel_management.hotel_manager.domain.HotelRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CreateHotelHandler {

    private final CreateHotelSteps steps;

    public CreateHotelHandler(HotelRepository repository) {
        this.steps = new CreateHotelSteps(repository);
    }

    public Mono<Void> execute(CreateHotelCommand command) {
        return Mono.just(command)
            .map(steps::createHotel)
            .flatMap(steps::assertUnique)
            .flatMap(steps::save);
    }

}
