package pl.com.marcinkrol.cms.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Movie {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String description;

    @ElementCollection
    private Set<String> actors;

    @ElementCollection
    private Set<String> genres;

    private Integer minAge;
    private Integer length;

    @OneToOne
    private Pricing pricing;

    Movie() {
    }

    public Movie(CreateMovieCommand cmd) {
        this.title = cmd.getTitle();
        this.description = cmd.getDescription();
        this.actors = cmd.getActors();
        this.genres = cmd.getGenres();
        this.minAge = cmd.getMinAge();
        this.length = cmd.getLength();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Set<String> getActors() {
        return actors;
    }

    public Set<String> getGenres() {
        return genres;
    }

    public Integer getMinAge() {
        return minAge;
    }

    public Integer getLength() {
        return length;
    }

    public Pricing getPricing() {
        return pricing;
    }

    public void definePricing(DefineMoviePricesCommand cmd) {
        if (pricing == null)
            pricing = new Pricing(cmd);
        else
            pricing.update(cmd);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        return id != null ? id.equals(movie.id) : movie.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}
