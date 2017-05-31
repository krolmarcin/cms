package pl.com.marcinkrol.cms.application;

import pl.com.marcinkrol.cms.domain.CreateCinemaCommand;

public interface CinemaManager {

    void create(CreateCinemaCommand cmd);

}
