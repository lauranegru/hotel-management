package hotel_management.hotel_manager.api.queries.get_hotel;

import hotel_management.hotel_manager.application.queries.get_hotel.GetHotelHandler;
import hotel_management.hotel_manager.application.queries.get_hotel.GetHotelQuery;
import hotel_management.hotel_manager.application.views.HotelView;
import hotel_management.hotel_manager.domain.HotelNotFound;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.OK;

@RestController
public class GetHotelController {

    private final GetHotelHandler handler;

    public GetHotelController(GetHotelHandler handler) {
        this.handler = handler;
    }

    @ResponseStatus(OK)
    @GetMapping("/hotels/queries/get-hotel")
    public Mono<HotelView> execute(GetHotelQuery query) {
        return handler.execute(query)
            .switchIfEmpty(Mono.error(HotelNotFound::new));
    }

}
