package hotel_management.hotel_manager.api;

import hotel_management.shared.api.rest.RestRequest;

import java.util.UUID;

import static hotel_management.shared.api.rest.RestRequestBuilder.request;

public class GetHotelRequestGenerator {

    private String id;

    private GetHotelRequestGenerator() {
        id(UUID.randomUUID().toString());
    }

    public static GetHotelRequestGenerator getHotelRequest() {
        return new GetHotelRequestGenerator();
    }

    public GetHotelRequestGenerator id(String id) {
        this.id = id;
        return this;
    }

    public RestRequest build() {
        return request()
            .method("GET")
            .path("/hotels/queries/get-hotel?id=" + id)
            .build();
    }

}
