package hotel_management.hotel_manager.domain;

import reactor.core.publisher.Mono;

public interface HotelRepository {

    Mono<Hotel> find(HotelId id);

    void save(Hotel hotel);

    void delete();

}
