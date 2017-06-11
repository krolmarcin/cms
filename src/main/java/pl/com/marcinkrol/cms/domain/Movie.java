package pl.com.marcinkrol.cms.domain;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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

}
