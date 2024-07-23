package hotel_management.shared.api.rest;

import java.util.Optional;

public class RestRequestGenerator {

    private String method;
    private String path;
    private String body;

    public static RestRequestGenerator request() {
        return new RestRequestGenerator();
    }

    public RestRequestGenerator method(String method) {
        this.method = method;
        return this;
    }

    public RestRequestGenerator path(String path) {
        this.path = path;
        return this;
    }

    public RestRequestGenerator body(String body) {
        this.body = body;
        return this;
    }

    public RestRequest build() {
        return new RestRequest(method, path, Optional.ofNullable(body));
    }

}
