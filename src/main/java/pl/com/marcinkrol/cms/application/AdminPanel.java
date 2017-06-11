package pl.com.marcinkrol.cms.application;

import pl.com.marcinkrol.cms.domain.CreateCinemaCommand;
import pl.com.marcinkrol.cms.domain.CreateMovieCommand;

public interface AdminPanel {

    void createCinema(CreateCinemaCommand cmd);

    void createMovie(CreateMovieCommand cmd);

}
