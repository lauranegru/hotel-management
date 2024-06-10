package hotel_management.hotel_manager.api;

import hotel_management.hotel_manager.application.CreateHotelCommand;
import hotel_management.hotel_manager.application.CreateHotelHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
public class CreateHotelController {

    private final CreateHotelHandler handler;

    public CreateHotelController(CreateHotelHandler handler) {
        this.handler = handler;
    }

    @ResponseStatus(CREATED)
    @PostMapping("/hotels/commands/create-hotel")
    public void create(@RequestBody CreateHotelCommand command) {
        handler.execute(command);
    }

}
