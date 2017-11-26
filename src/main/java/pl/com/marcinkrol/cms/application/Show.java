package pl.com.marcinkrol.cms.application;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalTime;

public class Show implements Comparable {

    private Long id;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime time;

    public Show(Long id, LocalTime time) {
        this.id = id;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Override
    public int compareTo(Object o) {
        Show show = (Show) o;
        if (show.getTime().isBefore(time))
            return 1;
        else
            return -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Show show = (Show) o;

        if (id != null ? !id.equals(show.id) : show.id != null) return false;
        return time != null ? time.equals(show.time) : show.time == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }
}
