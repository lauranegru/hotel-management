package hotel_management.shared.api.rest;

import java.util.Optional;

public class RestResponseGenerator {

    private Integer status;
    private String body;

    public static RestResponseGenerator response() {
        return new RestResponseGenerator();
    }

    public RestResponseGenerator status(Integer status) {
        this.status = status;
        return this;
    }

    public RestResponseGenerator body(String body) {
        this.body = body;
        return this;
    }

    public RestResponse build() {
        return new RestResponse(status, Optional.ofNullable(body));
    }

}
