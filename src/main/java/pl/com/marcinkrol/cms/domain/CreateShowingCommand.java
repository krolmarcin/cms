package pl.com.marcinkrol.cms.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public class CreateShowingCommand implements Validatable {

    private Long movieId;

    private Long cinemaId;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
    private List<LocalDateTime> dates;

    private ShowingCalendar calendar;

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Long getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(Long cinemaId) {
        this.cinemaId = cinemaId;
    }

    public List<LocalDateTime> getDates() {
        return dates;
    }

    public void setDates(List<LocalDateTime> dates) {
        this.dates = dates;
    }

    public ShowingCalendar getCalendar() {
        return calendar;
    }

    public void setCalendar(ShowingCalendar calendar) {
        this.calendar = calendar;
    }

    @Override
    public void validate(ValidationErrors errors) {

    }

}
