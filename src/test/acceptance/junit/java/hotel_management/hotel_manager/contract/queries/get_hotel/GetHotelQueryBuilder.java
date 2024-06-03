package hotel_management.hotel_manager.contract.queries.get_hotel;

import hotel_management.hotel_manager.service.queries.GetHotelQuery;

import java.util.UUID;

public class GetHotelQueryBuilder {

    private UUID id;

    private GetHotelQueryBuilder() {
        id(UUID.randomUUID());
    }

    public static GetHotelQueryBuilder getHotelQuery() {
        return new GetHotelQueryBuilder();
    }

    public GetHotelQueryBuilder id(UUID id) {
        this.id = id;
        return this;
    }

    public GetHotelQueryBuilder id(String id) {
        this.id = UUID.fromString(id);
        return this;
    }

    public GetHotelQuery build() {
        return new GetHotelQuery(id);
    }

}
