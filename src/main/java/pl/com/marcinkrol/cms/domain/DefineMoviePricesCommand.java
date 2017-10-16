package pl.com.marcinkrol.cms.domain;

import java.math.BigDecimal;
import java.util.Map;

public class DefineMoviePricesCommand implements Validatable {

    private Long movieId;

    private Map<String, BigDecimal> prices;

    public DefineMoviePricesCommand() {

    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Map<String, BigDecimal> getPrices() {
        return prices;
    }

    public void setPrices(Map<String, BigDecimal> prices) {
        this.prices = prices;
    }

    @Override
    public void validate(ValidationErrors errors) {
        validateMovieId(errors);
        validatePrices(errors);
    }

    private void validateMovieId(ValidationErrors errors) {
        if (movieId == null)
            errors.add("movieId", REQUIRED_FIELD);
    }

    private void validatePrices(ValidationErrors errors) {
        if (prices == null)
            errors.add("prices", REQUIRED_FIELD);
        if (!prices.containsKey("regular"))
            errors.add("kind", "regular price is required");
        if (!prices.containsKey("student"))
            errors.add("kind", "student price is required");

        for (BigDecimal price : prices.values())
            validatePrice(errors, price);
    }

    private void validatePrice(ValidationErrors errors, BigDecimal price) {
        if (price.compareTo(BigDecimal.ZERO) < 0)
            errors.add("prices", POSITIVE_OR_ZERO_REQUIRED);
    }

}
