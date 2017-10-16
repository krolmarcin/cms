package pl.com.marcinkrol.cms.domain;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.LinkedList;
import java.util.List;

public class ShowingFactory {

    public List<Showing> createShowings(CreateShowingCommand cmd, Cinema cinema, Movie movie) {
        List<Showing> showings = new LinkedList<>();
        if (cmd.getDates() != null && cmd.getCalendar() == null)
            addShowingsForDates(cmd, cinema, movie, showings);
        else if (cmd.getCalendar() != null && cmd.getDates() == null)
            addShowingsForCalendar(cmd, cinema, movie, showings);
        return showings;
    }

    private void addShowingsForDates(CreateShowingCommand cmd, Cinema cinema, Movie movie, List<Showing> showings) {
        List<LocalDateTime> showingDates = cmd.getDates();
        for (LocalDateTime date : showingDates) {
            showings.add(new Showing(date, cinema, movie));
        }
    }

    private void addShowingsForCalendar(CreateShowingCommand cmd, Cinema cinema, Movie movie, List<Showing> showings) {
        ShowingCalendar calendar = cmd.getCalendar();
        List<String> weekDays = calendar.getWeekDays();
        for (String day : weekDays) {
            LocalDateTime showingDate = getFirstDayOfPeriod(calendar, day);
            for (String hour : calendar.getHours()) {
                LocalTime showTime = LocalTime.parse(hour, DateTimeFormatter.ofPattern("kk:mm"));
                LocalDateTime showingDateAndTime = showingDate
                        .withHour(showTime.getHour())
                        .withMinute(showTime.getMinute());
                while (showingIsBeforeUntilDate(calendar, showingDateAndTime)) {
                    showings.add(new Showing(showingDateAndTime, cinema, movie));
                    showingDateAndTime = showingDateAndTime.plusWeeks(1L);
                }
            }
        }

    }

    private boolean showingIsBeforeUntilDate(ShowingCalendar calendar, LocalDateTime showinDateAndTime) {
        LocalDateTime untilDate = LocalDateTime.parse(calendar.getUntilDate(), DateTimeFormatter.ofPattern("yyyy/MM/dd kk:mm"));
        return (showinDateAndTime != null) && showinDateAndTime.isBefore(untilDate) || showinDateAndTime.isEqual(untilDate);
    }

    private LocalDateTime getFirstDayOfPeriod(ShowingCalendar calendar, String day) {
        LocalDateTime fromDate = LocalDateTime.parse(calendar.getFromDate(), DateTimeFormatter.ofPattern("yyyy/MM/dd kk:mm"));
        return fromDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.valueOf(day.toUpperCase())));
    }

}
