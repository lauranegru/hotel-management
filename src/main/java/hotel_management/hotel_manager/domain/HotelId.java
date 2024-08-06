package hotel_management.hotel_manager.domain;

import java.util.Objects;
import java.util.UUID;

public class HotelId {

    private final UUID value;

    private HotelId(UUID value) {
        if (value == null)
            throw new InvalidHotelId("The hotel id should not be missing");

        this.value = value;
    }

    public static HotelId of(UUID value) {
        return new HotelId(value);
    }

    public UUID value() {
        return value;
    }

    @Override
    public boolean equals(Object object) {
        return (object instanceof HotelId that)
               && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return new StringBuilder("HotelId{")
            .append("value=").append(value)
            .append('}')
            .toString();
    }

}
