package hotel_management.shared.api.rest;

import static hotel_management.shared.api.rest.JsonGenerator.json;
import static hotel_management.shared.api.rest.RestResponseGenerator.response;

public class RestErrorGenerator {

    private String message;
    private String type;
    private Integer status;

    private RestErrorGenerator() {
    }

    public static RestErrorGenerator errorResponse() {
        return new RestErrorGenerator();
    }

    public RestErrorGenerator message(String message) {
        this.message = message;
        return this;
    }

    public RestErrorGenerator type(String type) {
        this.type = type;
        return this;
    }

    public RestErrorGenerator status(Integer status) {
        this.status = status;
        return this;
    }

    public RestResponse build() {
        var error = json()
            .set("error", json()
                .set("type", type)
                .set("message", message))
            .build();

        return response()
            .status(status)
            .body(error)
            .build();
    }

}
