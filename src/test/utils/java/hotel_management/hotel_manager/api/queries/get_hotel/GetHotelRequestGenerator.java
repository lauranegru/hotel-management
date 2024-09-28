package hotel_management.hotel_manager.api.queries.get_hotel;

import hotel_management.shared.api.rest.RestRequest;

import java.util.UUID;

import static hotel_management.hotel_manager.domain.HotelIdGenerator.anyHotelIdValue;
import static hotel_management.shared.api.rest.RestRequestGenerator.request;

public class GetHotelRequestGenerator {

    private String id;

    private GetHotelRequestGenerator() {
        id(anyHotelIdValue());
    }

    public static RestRequest anyGetHotelRequest() {
        return getHotelRequest().build();
    }

    public static GetHotelRequestGenerator getHotelRequest() {
        return new GetHotelRequestGenerator();
    }

    public GetHotelRequestGenerator id(String id) {
        this.id = id;
        return this;
    }

    public GetHotelRequestGenerator id(UUID id) {
        this.id = id.toString();
        return this;
    }

    public GetHotelRequestGenerator missingId() {
        this.id = null;
        return this;
    }

    public RestRequest build() {
        var query = (id == null) ? "" : ("?id=" + id);

        return request()
            .method("GET")
            .path("/hotels/queries/get-hotel" + query)
            .build();
    }

}
