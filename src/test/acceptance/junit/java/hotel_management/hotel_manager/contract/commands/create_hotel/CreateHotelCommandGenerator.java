package hotel_management.hotel_manager.contract.commands.create_hotel;

import hotel_management.hotel_manager.service.commands.create_hotel.CreateHotelCommand;

import java.util.UUID;

public class CreateHotelCommandGenerator {

    private static Integer uniqueId = 1;

    private UUID id;
    private String name;

    private CreateHotelCommandGenerator() {
        id(UUID.randomUUID());
        name("Hotel " + uniqueId);
        uniqueId++;
    }

    public static CreateHotelCommandGenerator createHotelCommand() {
        return new CreateHotelCommandGenerator();
    }

    public CreateHotelCommandGenerator id(UUID id) {
        this.id = id;
        return this;
    }

    public CreateHotelCommandGenerator id(String id) {
        this.id = UUID.fromString(id);
        return this;
    }

    public CreateHotelCommandGenerator name(String name) {
        this.name = name;
        return this;
    }

    public CreateHotelCommandGenerator blankName() {
        this.name = "";
        return this;
    }

    public CreateHotelCommand build() {
        return new CreateHotelCommand(id, name);
    }
}