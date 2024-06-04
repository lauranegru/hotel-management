package hotel_management.shared.api.rest;

import java.util.Optional;

public class RestResponseBuilder {

    private Integer status;
    private String body;

    public static RestResponseBuilder response() {
        return new RestResponseBuilder();
    }

    public RestResponseBuilder status(Integer status) {
        this.status = status;
        return this;
    }

    public RestResponseBuilder body(String body) {
        this.body = body;
        return this;
    }

    public RestResponse build() {
        return new RestResponse(status, Optional.ofNullable(body));
    }

}
