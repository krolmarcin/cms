package pl.com.marcinkrol.cms.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

public class ShowingFactory {

    public List<Showing> createShowings(CreateShowingCommand cmd, Cinema cinema, Movie movie) {
        List<Showing> showings = new LinkedList<>();
        if (cmd.getDates() != null)
            addShowingsByDates(cinema, movie, cmd, showings);
        if (cmd.getCalendar() != null)
            addShowingsByCalendar(cinema, movie, cmd, showings);
        return showings;
    }

    private void addShowingsByDates(Cinema cinema, Movie movie, CreateShowingCommand cmd, List<Showing> showings) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd kk:mm");
        for (String date : cmd.getDates()) {
            LocalDateTime resultDate = LocalDateTime.parse(date, dtf);
            showings.add(new Showing(resultDate, cinema, movie));
        }
    }

    private void addShowingsByCalendar(Cinema cinema, Movie movie, CreateShowingCommand cmd, List<Showing> showings) {

    }

}
