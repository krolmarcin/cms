package pl.com.marcinkrol.cms.infrastructure;

import org.springframework.context.annotation.Bean;
import pl.com.marcinkrol.cms.application.CinemaCatalog;
import pl.com.marcinkrol.cms.application.implementation.StandardAdminPanel;
import pl.com.marcinkrol.cms.domain.CinemaRepository;
import pl.com.marcinkrol.cms.domain.MovieRepository;
import pl.com.marcinkrol.cms.domain.PricingRepository;
import pl.com.marcinkrol.cms.domain.ShowingRepository;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public StandardAdminPanel standardAdminPanel(CinemaRepository cinemaRepository, MovieRepository movieRepository, ShowingRepository showingRepository, PricingRepository pricingRepository) {
        return new StandardAdminPanel(cinemaRepository, movieRepository, showingRepository, pricingRepository);
    }

    @Bean
    public CinemaRepository cinemaRepository() {
        return new JPACinemaRepository();
    }

    @Bean
    public CinemaCatalog cinemaCatalog() {
        return new JPACinemaCatalog();
    }

    @Bean
    public MovieRepository movieRepository() {
        return new JPAMovieRepository();
    }

    @Bean
    public ShowingRepository showingRepository() {
        return new JPAShowingRepository();
    }

    @Bean
    public PricingRepository pricingRepository() {
        return new JPAPricingRepository();
    }
}
