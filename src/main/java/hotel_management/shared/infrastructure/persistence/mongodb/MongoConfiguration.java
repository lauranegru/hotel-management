package hotel_management.shared.infrastructure.persistence.mongodb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

@Configuration
public class MongoConfiguration {

    @Bean
    ReactiveMongoTemplate reactiveMongoTemplate(
        ReactiveMongoDatabaseFactory factory,
        MappingMongoConverter converter
    ) {
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return new ReactiveMongoTemplate(factory, converter);
    }

}
