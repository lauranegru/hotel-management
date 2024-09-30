package hotel_management.shared.api.rest;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.function.Consumer;

public class UriGenerator {

    private PathGenerator path;
    private QueryGenerator query;

    private UriGenerator() {
        path = PathGenerator.path();
        query = QueryGenerator.query();
    }

    public static UriGenerator uri() {
        return new UriGenerator();
    }

    public UriGenerator path(String path) {
        this.path.template(path);
        return this;
    }

    public UriGenerator path(PathGenerator path) {
        this.path = path;
        return this;
    }

    public UriGenerator path(Consumer<PathGenerator> operator) {
        operator.accept(path);
        return this;
    }

    public UriGenerator query(QueryGenerator query) {
        this.query = query;
        return this;
    }

    public UriGenerator query(Consumer<QueryGenerator> operator) {
        operator.accept(query);
        return this;
    }

    public String build() {
        return path.build() + query.build();
    }

    public static class PathGenerator {

        private String template;
        private final Map<String, String> params;

        private PathGenerator() {
            template = "";
            params = new HashMap<>();
        }

        public static PathGenerator path() {
            return new PathGenerator();
        }

        public PathGenerator template(String template) {
            this.template = template;
            return this;
        }

        public PathGenerator param(String key, String value) {
            params.put(key, value);
            return this;
        }

        public PathGenerator missing(String key) {
            params.remove(key);
            return this;
        }

        public String build() {
            return params.entrySet().stream()
                .reduce(
                    template,
                    (path, param) -> path.replace("{" + param.getKey() + "}", param.getValue()),
                    (path, newPath) -> newPath
                );
        }

    }

    public static class QueryGenerator {

        private final Map<String, String> params;

        private QueryGenerator() {
            params = new LinkedHashMap<>();
        }

        public static QueryGenerator query() {
            return new QueryGenerator();
        }

        public QueryGenerator param(String key, String value) {
            params.put(key, value);
            return this;
        }

        public QueryGenerator missing(String key) {
            params.remove(key);
            return this;
        }

        public String build() {
            return params.entrySet().stream()
                .reduce(
                    new StringJoiner("&", "?", "").setEmptyValue(""),
                    (query, param) -> query.add(param.getKey() + "=" + param.getValue()),
                    (query, newQuery) -> newQuery
                ).toString();
        }
    }

}
