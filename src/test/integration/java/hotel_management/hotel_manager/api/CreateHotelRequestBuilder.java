package hotel_management.hotel_manager.api;

import hotel_management.shared.api.rest.JsonBuilder;
import hotel_management.shared.api.rest.RestRequest;

import java.util.UUID;

import static hotel_management.shared.api.rest.JsonBuilder.json;
import static hotel_management.shared.api.rest.RestRequestBuilder.request;

public class CreateHotelRequestBuilder {

    private static Integer uniqueId = 1;

    private final JsonBuilder json;

    private CreateHotelRequestBuilder() {
        json = json();
        id(UUID.randomUUID());
        name("Hotel " + uniqueId);
        uniqueId++;
    }

    public static RestRequest anyCreateHotelRequest() {
        return createHotelRequest().build();
    }

    public static CreateHotelRequestBuilder createHotelRequest() {
        return new CreateHotelRequestBuilder();
    }

    public CreateHotelRequestBuilder id(String id) {
        json.set("id", id);
        return this;
    }

    public CreateHotelRequestBuilder id(UUID id) {
        json.set("id", id);
        return this;
    }

    public CreateHotelRequestBuilder name(String name) {
        json.set("name", name);
        return this;
    }

    public RestRequest build() {
        return request()
            .method("POST")
            .path("/hotels/commands/create-hotel")
            .body(json.build())
            .build();
    }

}
