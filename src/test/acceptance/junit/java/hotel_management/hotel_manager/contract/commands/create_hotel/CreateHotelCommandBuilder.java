package hotel_management.hotel_manager.contract.commands.create_hotel;

import hotel_management.hotel_manager.service.commands.CreateHotelCommand;

import java.util.UUID;

public class CreateHotelCommandBuilder {

    private static Integer uniqueId = 1;

    private UUID id;
    private String name;

    private CreateHotelCommandBuilder() {
        id(UUID.randomUUID());
        name("Hotel " + uniqueId);
        uniqueId++;
    }

    public static CreateHotelCommandBuilder createHotelCommand() {
        return new CreateHotelCommandBuilder();
    }

    public CreateHotelCommandBuilder id(UUID id) {
        this.id = id;
        return this;
    }

    public CreateHotelCommandBuilder id(String id) {
        this.id = UUID.fromString(id);
        return this;
    }

    public CreateHotelCommandBuilder name(String name) {
        this.name = name;
        return this;
    }

    public CreateHotelCommand build() {
        return new CreateHotelCommand(id, name);
    }

}
