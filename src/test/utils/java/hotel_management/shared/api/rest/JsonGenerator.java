package hotel_management.shared.api.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonGenerator {

    private static final ObjectMapper mapper = new ObjectMapper();

    private final ObjectNode node;

    private JsonGenerator() {
        node = mapper.createObjectNode();
    }

    public static JsonGenerator json() {
        return new JsonGenerator();
    }

    public JsonGenerator set(String key, Object value) {
        node.set(key, mapper.valueToTree(value));
        return this;
    }

    public JsonGenerator set(String key, JsonGenerator json) {
        node.set(key, json.node);
        return this;
    }

    public JsonGenerator remove(String key) {
        node.remove(key);
        return this;
    }

    public String build() {
        return node.toString();
    }

}
