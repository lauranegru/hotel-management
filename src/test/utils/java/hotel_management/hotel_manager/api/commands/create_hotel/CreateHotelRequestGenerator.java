package hotel_management.hotel_manager.api.commands.create_hotel;

import hotel_management.shared.api.rest.JsonGenerator;
import hotel_management.shared.api.rest.RestRequest;

import java.util.List;
import java.util.UUID;

import static hotel_management.shared.api.rest.JsonGenerator.json;
import static hotel_management.shared.api.rest.RestRequestGenerator.request;

public class CreateHotelRequestGenerator {

    private static Integer uniqueId = 1;

    private final JsonGenerator json;

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

    public CreateHotelRequestGenerator missingName() {
        json.remove("name");
        return this;
    }

    public CreateHotelRequestGenerator invalidNameValue() {
        json.set("name", "");
        return this;
    }

    public CreateHotelRequestGenerator invalidNameType() {
        json.set("name", List.of("invalid-type"));
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
