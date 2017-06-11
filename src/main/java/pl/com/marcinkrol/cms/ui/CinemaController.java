package pl.com.marcinkrol.cms.ui;

import org.springframework.web.bind.annotation.*;
import pl.com.marcinkrol.cms.application.CinemaCatalog;
import pl.com.marcinkrol.cms.application.CinemaDto;
import pl.com.marcinkrol.cms.application.AdminPanel;
import pl.com.marcinkrol.cms.domain.CreateCinemaCommand;

import java.util.List;

@RestController
@RequestMapping("/cinemas")
public class CinemaController {

    private AdminPanel adminPanel;
    private CinemaCatalog cinemaCatalog;

    public CinemaController(AdminPanel adminPanel, CinemaCatalog cinemaCatalog) {
        this.adminPanel = adminPanel;
        this.cinemaCatalog = cinemaCatalog;
    }

    @PutMapping
    public void createCinema(@RequestBody CreateCinemaCommand cmd) {
        adminPanel.createCinema(cmd);
    }

    @GetMapping
    public List<CinemaDto> showAllCinemas() {
        return cinemaCatalog.getCinemas();
    }

}
