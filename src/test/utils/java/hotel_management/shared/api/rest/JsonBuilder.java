package hotel_management.shared.api.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonBuilder {

    private static final ObjectMapper mapper = new ObjectMapper();

    private final ObjectNode node;

    private JsonBuilder() {
        node = mapper.createObjectNode();
    }

    public static JsonBuilder json() {
        return new JsonBuilder();
    }

    public JsonBuilder set(String key, Object value) {
        node.set(key, mapper.valueToTree(value));
        return this;
    }

    public JsonBuilder set(String key, JsonBuilder json) {
        node.set(key, json.node);
        return this;
    }

    public JsonBuilder remove(String key) {
        node.remove(key);
        return this;
    }

    public String build() {
        return node.toString();
    }

}
