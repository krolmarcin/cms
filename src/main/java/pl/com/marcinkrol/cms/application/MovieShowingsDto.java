package pl.com.marcinkrol.cms.application;

import java.util.List;

public class MovieShowingsDto {

    private MovieDto movie;

    private List<ShowingDto> shows;

    public MovieDto getMovie() {
        return movie;
    }

    public void setMovie(MovieDto movie) {
        this.movie = movie;
    }

    public List<ShowingDto> getShows() {
        return shows;
    }

    public void setShows(List<ShowingDto> shows) {
        this.shows = shows;
    }

}
