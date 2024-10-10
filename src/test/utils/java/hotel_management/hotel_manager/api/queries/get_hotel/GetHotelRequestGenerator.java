package hotel_management.hotel_manager.api.queries.get_hotel;

import hotel_management.shared.api.rest.RestRequest;
import hotel_management.shared.api.rest.UriGenerator.QueryGenerator;

import java.util.UUID;

import static hotel_management.hotel_manager.domain.HotelIdGenerator.anyHotelIdValue;
import static hotel_management.shared.api.rest.RestRequestGenerator.request;

public class GetHotelRequestGenerator {

    private final QueryGenerator query;

    private GetHotelRequestGenerator() {
        query = QueryGenerator.query();
        id(anyHotelIdValue());
    }

    public static RestRequest anyGetHotelRequest() {
        return getHotelRequest().build();
    }

    public static GetHotelRequestGenerator getHotelRequest() {
        return new GetHotelRequestGenerator();
    }

    public GetHotelRequestGenerator id(String id) {
        query.param("id", id);
        return this;
    }

    public GetHotelRequestGenerator id(UUID id) {
        query.param("id", id.toString());
        return this;
    }

    public GetHotelRequestGenerator missingId() {
        query.missing("id");
        return this;
    }

    public GetHotelRequestGenerator invalidIdType() {
        query.param("id", "invalid-type");
        return this;
    }

    public RestRequest build() {
        return request()
            .method("GET")
            .uri(u -> u
                .path("/hotels/queries/get-hotel")
                .query(query))
            .build();
    }

}
