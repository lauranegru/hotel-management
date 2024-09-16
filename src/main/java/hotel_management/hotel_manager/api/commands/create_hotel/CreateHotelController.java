package hotel_management.hotel_manager.api.commands.create_hotel;

import hotel_management.hotel_manager.application.commands.create_hotel.CreateHotelCommand;
import hotel_management.hotel_manager.application.commands.create_hotel.CreateHotelHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
public class CreateHotelController {

    private final CreateHotelHandler handler;

    public CreateHotelController(CreateHotelHandler handler) {
        this.handler = handler;
    }

    @ResponseStatus(CREATED)
    @PostMapping("/hotels/commands/create-hotel")
    public Mono<Void> create(@RequestBody CreateHotelCommand command) {
        return Mono.<Void>fromRunnable(() -> handler.execute(command).block())
            .subscribeOn(Schedulers.boundedElastic());
    }

}
