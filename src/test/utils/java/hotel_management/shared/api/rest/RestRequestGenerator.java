package hotel_management.shared.api.rest;

import java.util.Optional;
import java.util.function.Consumer;

public class RestRequestGenerator {

    private final UriGenerator uri;
    private String method;
    private String body;

    private RestRequestGenerator() {
        uri = UriGenerator.uri();
    }

    public static RestRequestGenerator request() {
        return new RestRequestGenerator();
    }

    public RestRequestGenerator method(String method) {
        this.method = method;
        return this;
    }

    public RestRequestGenerator uri(Consumer<UriGenerator> operator) {
        operator.accept(uri);
        return this;
    }

    public RestRequestGenerator path(String path) {
        uri.path(path);
        return this;
    }

    public RestRequestGenerator body(String body) {
        this.body = body;
        return this;
    }

    public RestRequest build() {
        return new RestRequest(method, uri.build(), Optional.ofNullable(body));
    }

}
