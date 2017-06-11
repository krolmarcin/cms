package pl.com.marcinkrol.cms.application.implementation;

import org.springframework.transaction.annotation.Transactional;
import pl.com.marcinkrol.cms.application.AdminPanel;
import pl.com.marcinkrol.cms.domain.*;

@Transactional
public class StandardAdminPanel implements AdminPanel {

    private CinemaRepository cinemaRepository;
    private MovieRepository movieRepository;

    public StandardAdminPanel(CinemaRepository cinemaRepository, MovieRepository movieRepository) {
        this.cinemaRepository = cinemaRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public void createCinema(CreateCinemaCommand cmd) {
        Cinema cinema = new Cinema(cmd);
        checkCinemaExists(cinema);
        cinemaRepository.put(cinema);
    }

    @Override
    public void createMovie(CreateMovieCommand cmd) {
        Movie movie = new Movie(cmd);
        movieRepository.put(movie);

    }

    private void checkCinemaExists(Cinema cinema) {
        if (cinemaRepository.exists(cinema.getName(), cinema.getCity())) {
            throw new InvalidActionException("Cinema has already been created");
        }
    }

}
