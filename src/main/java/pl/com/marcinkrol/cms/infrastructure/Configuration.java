package pl.com.marcinkrol.cms.infrastructure;

import org.springframework.context.annotation.Bean;
import pl.com.marcinkrol.cms.application.implementation.StandardCinemaManager;
import pl.com.marcinkrol.cms.domain.CinemaRepository;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public StandardCinemaManager standardCinemaManager(CinemaRepository cinemaRepository) {
        return new StandardCinemaManager(cinemaRepository);
    }

    @Bean
    public CinemaRepository cinemaRepository() {
        return new JPACinemaRepository();
    }

}
