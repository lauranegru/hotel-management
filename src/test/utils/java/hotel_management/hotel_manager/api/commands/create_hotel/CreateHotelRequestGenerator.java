package hotel_management.hotel_manager.api.commands.create_hotel;

import hotel_management.shared.api.rest.JsonGenerator;
import hotel_management.shared.api.rest.RestRequest;

import java.util.List;
import java.util.UUID;

import static hotel_management.hotel_manager.domain.HotelIdGenerator.anyHotelIdValue;
import static hotel_management.hotel_manager.domain.HotelNameGenerator.anyHotelNameValue;
import static hotel_management.shared.api.rest.JsonGenerator.json;
import static hotel_management.shared.api.rest.RestRequestGenerator.request;

public class CreateHotelRequestGenerator {

    private final JsonGenerator json;

    private CreateHotelRequestGenerator() {
        json = json();
        id(anyHotelIdValue());
        name(anyHotelNameValue());
    }

    public static CreateHotelRequestGenerator createHotelRequest() {
        return new CreateHotelRequestGenerator();
    }

    public static RestRequest anyCreateHotelRequest() {
        return createHotelRequest().build();
    }

    public CreateHotelRequestGenerator id(String id) {
        json.set("id", id);
        return this;
    }

    public CreateHotelRequestGenerator id(UUID id) {
        json.set("id", id);
        return this;
    }

    public CreateHotelRequestGenerator missingId() {
        json.remove("id");
        return this;
    }

    public CreateHotelRequestGenerator invalidIdType() {
        json.set("id", "invalid-type");
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
