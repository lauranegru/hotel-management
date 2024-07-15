package hotel_management.shared.api.rest;

import java.util.Optional;

public class RestRequestBuilder {

    private String method;
    private String path;
    private String body;

    public static RestRequestBuilder request() {
        return new RestRequestBuilder();
    }

    public RestRequestBuilder method(String method) {
        this.method = method;
        return this;
    }

    public RestRequestBuilder path(String path) {
        this.path = path;
        return this;
    }

    public RestRequestBuilder body(String body) {
        this.body = body;
        return this;
    }

    public RestRequest build() {
        return new RestRequest(method, path, Optional.ofNullable(body));
    }

}
