package hotel_management.hotel_manager.api.queries.get_hotel;

import hotel_management.shared.api.rest.JsonGenerator;
import hotel_management.shared.api.rest.RestResponse;

import java.util.UUID;

import static hotel_management.hotel_manager.domain.HotelIdGenerator.anyHotelIdValue;
import static hotel_management.hotel_manager.domain.HotelNameGenerator.anyHotelNameValue;
import static hotel_management.shared.api.rest.JsonGenerator.json;
import static hotel_management.shared.api.rest.RestResponseGenerator.response;

public class GetHotelResponseGenerator {

    private Integer status;
    private final JsonGenerator json;

    private GetHotelResponseGenerator() {
        json = json();
        status(200);
        id(anyHotelIdValue());
        name(anyHotelNameValue());
    }

    public static GetHotelResponseGenerator getHotelResponse() {
        return new GetHotelResponseGenerator();
    }

    public GetHotelResponseGenerator status(Integer status) {
        this.status = status;
        return this;
    }

    public GetHotelResponseGenerator id(String id) {
        json.set("id", id);
        return this;
    }

    public GetHotelResponseGenerator id(UUID id) {
        json.set("id", id.toString());
        return this;
    }

    public GetHotelResponseGenerator name(String name) {
        json.set("name", name);
        return this;
    }

    public RestResponse build() {
        return response()
            .status(status)
            .body(json.build())
            .build();
    }

}
