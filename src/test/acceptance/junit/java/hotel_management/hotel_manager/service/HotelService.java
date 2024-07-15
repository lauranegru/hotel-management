package hotel_management.hotel_manager.service;

import hotel_management.hotel_manager.service.commands.create_hotel.CreateHotelCommand;
import hotel_management.hotel_manager.service.queries.get_hotel.GetHotelQuery;
import hotel_management.hotel_manager.service.views.HotelView;
import org.springframework.web.reactive.function.client.WebClient;

import static org.springframework.http.MediaType.APPLICATION_JSON;

public class HotelService {

    private final WebClient client;

    public HotelService(WebClient client) {
        this.client = client;
    }

    public void execute(CreateHotelCommand command) {
        client.post()
            .uri("/hotels/commands/create-hotel")
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON)
            .bodyValue(command)
            .retrieve()
            .bodyToMono(Void.class)
            .block();
    }

    public HotelView execute(GetHotelQuery query) {
        return client.get()
            .uri("/hotels/queries/get-hotel?id={id}", query.id())
            .accept(APPLICATION_JSON)
            .retrieve()
            .bodyToMono(HotelView.class)
            .block();
    }

}
