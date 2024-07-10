package hotel_management.hotel_manager.application;

import hotel_management.hotel_manager.domain.HotelRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GetHotelHandler {

    private final HotelRepository repository;
    private final HotelMapper mapper;

    public GetHotelHandler(HotelRepository repository, HotelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Optional<HotelView> execute(GetHotelQuery query) {
        return repository.find(query.id())
            .map(mapper::toView);
    }

}
