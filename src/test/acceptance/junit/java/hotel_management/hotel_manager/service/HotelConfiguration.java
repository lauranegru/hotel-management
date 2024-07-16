package hotel_management.hotel_manager.service;

import hotel_management.shared.WebClientExceptionTranslator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class HotelConfiguration {

    @Bean
    public HotelService hotelService(WebClient.Builder webClientBuilder) {
        var client = webClientBuilder
            .baseUrl("http://localhost:8080")
            .build();

        return new HotelService(client, new WebClientExceptionTranslator());
    }

}
