package hotel_management.shared.api.rest;

import static hotel_management.shared.api.rest.JsonBuilder.json;
import static hotel_management.shared.api.rest.RestResponseBuilder.response;

public class RestErrorBuilder {

    private String message;
    private String type;
    private Integer status;

    private RestErrorBuilder() {
    }

    public static RestErrorBuilder errorResponse() {
        return new RestErrorBuilder();
    }

    public RestErrorBuilder message(String message) {
        this.message = message;
        return this;
    }

    public RestErrorBuilder type(String type) {
        this.type = type;
        return this;
    }

    public RestErrorBuilder status(Integer status) {
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
