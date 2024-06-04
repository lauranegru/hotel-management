package hotel_management.hotel_manager.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
public class CreateHotelController {

    @ResponseStatus(CREATED)
    @PostMapping("/hotels/commands/create-hotel")
    public void create(@RequestBody String request) {
        System.out.println(request);
    }

}
