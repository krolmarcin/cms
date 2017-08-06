package pl.com.marcinkrol.cms.application.implementation;

import org.springframework.transaction.annotation.Transactional;
import pl.com.marcinkrol.cms.application.AdminPanel;
import pl.com.marcinkrol.cms.domain.*;
import pl.com.marcinkrol.cms.infrastructure.JPAShowingRepository;

import java.util.List;

@Transactional
public class StandardAdminPanel implements AdminPanel {

    private CinemaRepository cinemaRepository;
    private MovieRepository movieRepository;
    private ShowingRepository showingRepository;

    public StandardAdminPanel(CinemaRepository cinemaRepository, MovieRepository movieRepository, ShowingRepository showingRepository) {
        this.cinemaRepository = cinemaRepository;
        this.movieRepository = movieRepository;
        this.showingRepository = showingRepository;
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

    @Override
    public void createShowing(CreateShowingCommand cmd) {
        Cinema cinema = cinemaRepository.get(cmd.getCinemaId());
        Movie movie = movieRepository.get(cmd.getMovieId());
        ShowingFactory showingFactory = new ShowingFactory();
        List<Showing> showings = showingFactory.createShowings(cmd, cinema, movie);
        for (Showing showing : showings)
            showingRepository.put(showing);
    }

    private void checkCinemaExists(Cinema cinema) {
        if (cinemaRepository.exists(cinema.getName(), cinema.getCity())) {
            throw new InvalidActionException("Cinema has already been created");
        }
    }

}
