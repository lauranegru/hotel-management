package hotel_management.hotel_manager.api;

import hotel_management.hotel_manager.application.GetHotelHandler;
import hotel_management.hotel_manager.application.GetHotelQuery;
import hotel_management.hotel_manager.application.HotelView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static org.springframework.http.HttpStatus.OK;

@RestController
public class GetHotelController {

    private final GetHotelHandler handler;

    public GetHotelController(GetHotelHandler handler) {
        this.handler = handler;
    }

    @ResponseStatus(OK)
    @GetMapping("/hotels/queries/get-hotel")
    public Optional<HotelView> execute(GetHotelQuery query) {
        return handler.execute(query);
    }

}
