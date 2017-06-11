package pl.com.marcinkrol.cms.ui;

import org.springframework.web.bind.annotation.*;
import pl.com.marcinkrol.cms.application.CinemaCatalog;
import pl.com.marcinkrol.cms.application.CinemaDto;
import pl.com.marcinkrol.cms.application.CinemaManager;
import pl.com.marcinkrol.cms.domain.CreateCinemaCommand;

import java.util.List;

@RestController
@RequestMapping("/cinemas")
public class CinemaController {

    private CinemaManager cinemaManager;
    private CinemaCatalog cinemaCatalog;

    public CinemaController(CinemaManager cinemaManager, CinemaCatalog cinemaCatalog) {
        this.cinemaManager = cinemaManager;
        this.cinemaCatalog = cinemaCatalog;
    }

    @PutMapping
    public void createCinema(@RequestBody CreateCinemaCommand cmd) {
        cinemaManager.create(cmd);
    }

    @GetMapping
    public List<CinemaDto> showAllCinemas() {
        return cinemaCatalog.getCinemas();
    }

}
