package hotel_management.hotel_manager.domain;

import reactor.core.publisher.Mono;

public interface HotelRepository {

    Mono<Hotel> find(HotelId id);

    Mono<Void> save(Hotel hotel);

    void delete();

}
