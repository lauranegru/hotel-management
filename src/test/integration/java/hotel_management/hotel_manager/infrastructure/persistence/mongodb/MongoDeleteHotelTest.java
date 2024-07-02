package hotel_management.hotel_manager.infrastructure.persistence.mongodb;

import hotel_management.hotel_manager.domain.HotelRepository;
import hotel_management.hotel_manager.infrastructure.persistence.contract.DeleteHotelTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MongoDeleteHotelTest extends DeleteHotelTest {

    private final MongoHotelRepository repository;

    @Autowired
    public MongoDeleteHotelTest(MongoHotelRepository repository) {
        this.repository = repository;
    }

    @Override
    protected HotelRepository repository() {
        return repository;
    }

}
