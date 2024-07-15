package hotel_management.hotel_manager.api.commands.create_hotel;

import hotel_management.shared.api.rest.JsonBuilder;
import hotel_management.shared.api.rest.RestRequest;

import java.util.UUID;

import static hotel_management.shared.api.rest.JsonBuilder.json;
import static hotel_management.shared.api.rest.RestRequestBuilder.request;

public class CreateHotelRequestGenerator {

    private static Integer uniqueId = 1;

    private final JsonBuilder json;

    private CreateHotelRequestGenerator() {
        json = json();
        id(UUID.randomUUID());
        name("Hotel " + uniqueId);
        uniqueId++;
    }

    public static CreateHotelRequestGenerator createHotelRequest() {
        return new CreateHotelRequestGenerator();
    }

    public CreateHotelRequestGenerator id(String id) {
        json.set("id", id);
        return this;
    }

    public CreateHotelRequestGenerator id(UUID id) {
        json.set("id", id);
        return this;
    }

    public CreateHotelRequestGenerator name(String name) {
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
