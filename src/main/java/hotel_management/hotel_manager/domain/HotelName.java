package hotel_management.hotel_manager.domain;

public class HotelName {

    private final String value;

    private HotelName(String value) {
        if (value == null)
            throw new InvalidHotelName("The hotel name should not be null");

        if (value.isBlank())
            throw new InvalidHotelName("The hotel name should not be blank");

        if (value.length() > 25)
            throw new InvalidHotelName("The hotel name should have a maximum of 25 characters");

        this.value = value;
    }

    public static HotelName of(String value) {
        return new HotelName(value);
    }

    public String value() {
        return value;
    }

}
