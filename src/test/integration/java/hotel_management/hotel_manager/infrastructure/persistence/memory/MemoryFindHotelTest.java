package hotel_management.hotel_manager.infrastructure.persistence.memory;

import hotel_management.hotel_manager.domain.HotelRepository;
import hotel_management.hotel_manager.infrastructure.persistence.contract.FindHotelTest;

public class MemoryFindHotelTest extends FindHotelTest {

    @Override
    protected HotelRepository repository() {
        return new MemoryHotelRepository();
    }

}
