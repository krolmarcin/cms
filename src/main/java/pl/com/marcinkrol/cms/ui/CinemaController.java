package pl.com.marcinkrol.cms.ui;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.com.marcinkrol.cms.application.CinemaManager;
import pl.com.marcinkrol.cms.domain.CreateCinemaCommand;

@RestController
public class CinemaController {

    private CinemaManager cinemaManager;

    public CinemaController(CinemaManager cinemaManager) {
        this.cinemaManager = cinemaManager;
    }

    @PutMapping("/cinemas")
    public void createCinema(@RequestBody CreateCinemaCommand cmd) {
        cinemaManager.create(cmd);
    }
}
