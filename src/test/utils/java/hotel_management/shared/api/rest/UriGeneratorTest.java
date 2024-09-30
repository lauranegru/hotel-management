package hotel_management.shared.api.rest;

import org.junit.jupiter.api.Test;

import static hotel_management.shared.api.rest.UriGenerator.uri;
import static org.assertj.core.api.Assertions.assertThat;

class UriGeneratorTest {

    @Test
    void generates_empty_uris() {
        var result = uri().build();

        var expected = "";

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void generates_uris_without_path_template() {
        var result = uri()
            .path("/hotels")
            .build();

        var expected = "/hotels";

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void generates_uris_with_path_template_and_no_path_parameters() {
        var result = uri()
            .path(p -> p
                .template("/hotels"))
            .build();

        var expected = "/hotels";

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void generates_uris_with_path_template_and_a_single_path_parameter() {
        var result = uri()
            .path(p -> p
                .template("/hotels/{hotel-id}")
                .param("hotel-id", "1"))
            .build();

        var expected = "/hotels/1";

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void generates_uris_with_path_template_and_multiple_path_parameters() {
        var result = uri()
             .path(p -> p
                 .template("/hotels/{hotel-id}/rooms/{room-id}")
                 .param("hotel-id", "1")
                 .param("room-id", "2"))
             .build();

        var expected = "/hotels/1/rooms/2";

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void generates_uris_with_path_template_and_partially_replaced_path_parameters() {
        var result = uri()
            .path(p -> p
                .template("/hotels/{hotel-id}/rooms/{room-id}")
                .param("hotel-id", "1"))
            .build();

        var expected = "/hotels/1/rooms/{room-id}";

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void generate_uris_with_path_template_and_no_replaced_path_parameters() {
        var result = uri()
            .path(p -> p
                .template("/hotels/{hotel-id}/rooms/{room-id}"))
            .build();

        var expected = "/hotels/{hotel-id}/rooms/{room-id}";

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void generates_uris_with_path_template_and_non_existing_path_parameters() {
        var result = uri()
            .path(p -> p
                .template("/hotels/{hotel-id}")
                .param("room-id", "2"))
            .build();

        var expected = "/hotels/{hotel-id}";

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void generate_uris_with_path_template_and_missing_path_parameters() {
        var result = uri()
            .path(p -> p
                .template("/hotels/{hotel-id}/rooms/{room-id}")
                .param("hotel-id", "1")
                .param("room-id", "2")
                .missing("hotel-id"))
            .build();

        var expected = "/hotels/{hotel-id}/rooms/2";

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void generate_uris_with_path_template_and_missing_non_existing_path_parameters() {
        var result = uri()
            .path(p -> p
                .template("/hotels/{hotel-id}")
                .param("hotel-id", "1")
                .missing("room-id"))
            .build();

        var expected = "/hotels/1";

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void generates_uris_with_no_query_parameters() {
        var result = uri()
            .query(q -> {})
            .build();

        var expected = "";

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void generates_uris_with_a_single_query_parameter() {
        var result = uri()
            .query(q -> q
                .param("hotel-id", "1"))
            .build();

        var expected = "?hotel-id=1";

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void generates_uris_with_multiple_query_parameters() {
        var result = uri()
            .query(q -> q
                .param("hotel-id", "1")
                .param("room-id", "2"))
            .build();

        var expected = "?hotel-id=1&room-id=2";

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void generates_uris_missing_query_parameters() {
        var result = uri()
            .query(q -> q
                .param("hotel-id", "1")
                .param("room-id", "2")
                .missing("hotel-id"))
            .build();

        var expected = "?room-id=2";

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void generates_uris_with_path_template_and_query_parameters() {
        var result = uri()
            .path(p -> p
                .template("/hotels/{hotel-id}/rooms/{room-id}")
                .param("hotel-id", "1")
                .param("room-id", "2"))
            .query(q -> q
                .param("hotel-name", "hotel-one")
                .param("room-name", "room-two"))
            .build();

        var expected = "/hotels/1/rooms/2?hotel-name=hotel-one&room-name=room-two";

        assertThat(result).isEqualTo(expected);
    }

}