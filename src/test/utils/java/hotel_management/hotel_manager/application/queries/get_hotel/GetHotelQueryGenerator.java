package hotel_management.hotel_manager.application.queries.get_hotel;

import java.util.UUID;

import static hotel_management.hotel_manager.domain.HotelIdGenerator.anyHotelIdValue;

public class GetHotelQueryGenerator {

    private UUID id;

    private GetHotelQueryGenerator() {
        id(anyHotelIdValue());
    }

    public static GetHotelQueryGenerator getHotelQuery() {
        return new GetHotelQueryGenerator();
    }

    public GetHotelQueryGenerator id(UUID id) {
        this.id = id;
        return this;
    }

    public GetHotelQueryGenerator id(String id) {
        this.id = UUID.fromString(id);
        return this;
    }

    public GetHotelQuery build() {
        return new GetHotelQuery(id);
    }

}
