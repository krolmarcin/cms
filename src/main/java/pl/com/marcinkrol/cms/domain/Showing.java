package pl.com.marcinkrol.cms.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Showing {

    @Id
    @GeneratedValue
    private Long Id;

    private LocalDateTime beginsAt;
    private LocalDateTime endsAt;

    @ManyToOne
    private Cinema cinema;

    @ManyToOne
    private Movie movie;

    Showing() {
    }

    public Showing(LocalDateTime date, Cinema cinema, Movie movie) {
        this.beginsAt = date;
        this.endsAt = beginsAt.plusMinutes(movie.getLength());
        this.cinema = cinema;
        this.movie = movie;
    }

    public Long getId() {
        return Id;
    }

    public LocalDateTime getBeginsAt() {
        return beginsAt;
    }

    public LocalDateTime getEndsAt() {
        return endsAt;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public Movie getMovie() {
        return movie;
    }

}
