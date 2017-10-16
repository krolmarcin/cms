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
        validateCinemaId(errors);
        validateMovieId(errors);
        validateDatesAndCalendar(errors);

    }

    private void validateMovieId(ValidationErrors errors) {
        if (movieId == null)
            errors.add("movieId", REQUIRED_FIELD);
    }

    private void validateCinemaId(ValidationErrors errors) {
        if (cinemaId == null)
            errors.add("cinemaId", REQUIRED_FIELD);
    }

    private void validateDatesAndCalendar(ValidationErrors errors) {
        if (calendar == null && dates == null)
            errors.add("calendar and dates", MAX_ONE_REQUIRED);
        else if (calendar != null && dates != null)
            errors.add("calendars and dates", MIN_ONE_REQUIRED);
        else if (dates != null)
            validateDates(errors);
        else
            calendar.validate(errors);
    }

    private void validateDates(ValidationErrors errors) {
        if (dates.isEmpty())
            errors.add("dates", REQUIRED_FIELD);
        if (dates.remove(null))
            errors.add("dates", NON_NULL_ELEMENT);
        for (LocalDateTime date : dates)
            if (date.isBefore(LocalDateTime.now())) {
                errors.add("dates", FUTURE_DATE_REQUIRED);
                break;
            }
    }

}
