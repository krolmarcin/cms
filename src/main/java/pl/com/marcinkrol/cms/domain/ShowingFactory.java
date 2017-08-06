package pl.com.marcinkrol.cms.domain;

import java.time.LocalDateTime;
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

    }

}
