package hotel_management.hotel_manager.infrastructure.persistence.memory;

import hotel_management.hotel_manager.domain.HotelRepository;
import hotel_management.hotel_manager.infrastructure.persistence.contract.DeleteHotelTest;

public class MemoryDeleteHotelTest extends DeleteHotelTest {

    @Override
    protected HotelRepository repository() {
        return new MemoryHotelRepository();
    }

}
