package pl.com.marcinkrol.cms.application.implementation;

import org.springframework.transaction.annotation.Transactional;
import pl.com.marcinkrol.cms.application.CinemaManager;
import pl.com.marcinkrol.cms.domain.Cinema;
import pl.com.marcinkrol.cms.domain.CinemaRepository;
import pl.com.marcinkrol.cms.domain.CreateCinemaCommand;

@Transactional
public class StandardCinemaManager implements CinemaManager {

    private CinemaRepository cinemaRepository;

    public StandardCinemaManager(CinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }

    @Override
    public void create(CreateCinemaCommand cmd) {
        Cinema cinema = new Cinema(cmd);
        //TODO isExist(cinema)
        cinemaRepository.put(cinema);
    }

}
