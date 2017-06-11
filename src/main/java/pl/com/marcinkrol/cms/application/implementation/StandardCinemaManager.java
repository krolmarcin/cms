package pl.com.marcinkrol.cms.application.implementation;

import org.springframework.transaction.annotation.Transactional;
import pl.com.marcinkrol.cms.application.CinemaManager;
import pl.com.marcinkrol.cms.domain.Cinema;
import pl.com.marcinkrol.cms.domain.CinemaRepository;
import pl.com.marcinkrol.cms.domain.CreateCinemaCommand;
import pl.com.marcinkrol.cms.domain.InvalidActionException;

@Transactional
public class StandardCinemaManager implements CinemaManager {

    private CinemaRepository cinemaRepository;

    public StandardCinemaManager(CinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }

    @Override
    public void create(CreateCinemaCommand cmd) {
        Cinema cinema = new Cinema(cmd);
        checkCinemaExists(cinema);
        cinemaRepository.put(cinema);
    }

    private void checkCinemaExists(Cinema cinema) {
        if (cinemaRepository.exists(cinema.getName(), cinema.getCity())) {
            throw new InvalidActionException("Cinema has already been created");
        }
    }

}
