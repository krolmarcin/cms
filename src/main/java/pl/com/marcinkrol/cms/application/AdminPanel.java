package pl.com.marcinkrol.cms.application;

import pl.com.marcinkrol.cms.domain.CreateCinemaCommand;
import pl.com.marcinkrol.cms.domain.CreateMovieCommand;
import pl.com.marcinkrol.cms.domain.CreateShowingCommand;
import pl.com.marcinkrol.cms.domain.DefineMoviePricesCommand;

public interface AdminPanel {

    void createCinema(CreateCinemaCommand cmd);

    void createMovie(CreateMovieCommand cmd);

    void createShowing(CreateShowingCommand cmd);

    void defineMoviePrices(DefineMoviePricesCommand cmd);

}
