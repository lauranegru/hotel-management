package hotel_management.hotel_manager.infrastructure.persistence.mongodb;

import hotel_management.hotel_manager.domain.HotelRepository;
import hotel_management.hotel_manager.infrastructure.persistence.contract.SaveHotelTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MongoSaveHotelTest extends SaveHotelTest {

    private final MongoHotelRepository repository;

    @Autowired
    public MongoSaveHotelTest(MongoHotelRepository repository) {
        this.repository = repository;
    }

    @Override
    protected HotelRepository repository() {
        return repository;
    }

}