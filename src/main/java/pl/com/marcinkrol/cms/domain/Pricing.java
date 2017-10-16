package pl.com.marcinkrol.cms.domain;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Pricing {

    @Id
    @GeneratedValue
    private Long id;

    @ElementCollection
    private Map<String, BigDecimal> prices = new HashMap<>();

    Pricing() {
    }

    public Pricing(DefineMoviePricesCommand cmd) {
        prices.putAll(cmd.getPrices());
    }

    public Long getId() {
        return id;
    }

    public Map<String, BigDecimal> getPrices() {
        return prices;
    }

    public void update(DefineMoviePricesCommand cmd) {
        prices.clear();
        prices.putAll(cmd.getPrices());
    }

}
