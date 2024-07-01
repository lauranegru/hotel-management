package hotel_management.hotel_manager.infrastructure.persistence.mongodb;

import hotel_management.hotel_manager.domain.HotelRepository;
import hotel_management.hotel_manager.infrastructure.persistence.contract.FindHotelTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MongoFindHotelTest extends FindHotelTest {

    private final MongoHotelRepository repository;

    @Autowired
    public MongoFindHotelTest(MongoHotelRepository repository) {
        this.repository = repository;
    }

    @Override
    protected HotelRepository repository() {
        return repository;
    }

}
