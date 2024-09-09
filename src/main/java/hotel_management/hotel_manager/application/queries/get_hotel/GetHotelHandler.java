package hotel_management.hotel_manager.application.queries.get_hotel;

import hotel_management.hotel_manager.application.views.HotelMapper;
import hotel_management.hotel_manager.application.views.HotelView;
import hotel_management.hotel_manager.domain.HotelId;
import hotel_management.hotel_manager.domain.HotelRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class GetHotelHandler {

    private final HotelRepository repository;
    private final HotelMapper mapper;

    public GetHotelHandler(HotelRepository repository, HotelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Mono<HotelView> execute(GetHotelQuery query) {
        return repository.find(HotelId.of(query.id()))
            .map(mapper::toView);
    }

}
