package hotel_management.hotel_manager.domain;

public class HotelNameGenerator {

    private static Integer uniqueId = 1;

    public static HotelName anyHotelName() {
        return HotelName.of("Hotel " + uniqueId++);
    }

    public static String anyHotelNameValue() {
        return anyHotelName().value();
    }

}
