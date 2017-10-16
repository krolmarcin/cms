package pl.com.marcinkrol.cms.ui;

import org.springframework.web.bind.annotation.*;
import pl.com.marcinkrol.cms.application.AdminPanel;
import pl.com.marcinkrol.cms.domain.CreateMovieCommand;
import pl.com.marcinkrol.cms.domain.DefineMoviePricesCommand;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private AdminPanel adminPanel;

    public MovieController(AdminPanel adminPanel) {
        this.adminPanel = adminPanel;
    }

    @PutMapping
    public void create(@RequestBody CreateMovieCommand cmd) {
        adminPanel.createMovie(cmd);
    }

    @PutMapping("/{movieId}/prices")
    public void defineMoviePrices(@PathVariable Long movieId, @RequestBody Map<String, BigDecimal> pricesMap){
        DefineMoviePricesCommand cmd = new DefineMoviePricesCommand();
        cmd.setPrices(pricesMap);
        cmd.setMovieId(movieId);
        adminPanel.defineMoviePrices(cmd);
    }

}
