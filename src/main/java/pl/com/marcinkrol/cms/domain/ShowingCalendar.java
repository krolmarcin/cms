package pl.com.marcinkrol.cms.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class ShowingCalendar {

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
    private LocalDateTime fromDate;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
    private LocalDateTime untilDate;

    private List<DayOfWeek> weekDays;

    private List<LocalTime> hours;

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDateTime fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDateTime getUntilDate() {
        return untilDate;
    }

    public void setUntilDate(LocalDateTime untilDate) {
        this.untilDate = untilDate;
    }

    public List<DayOfWeek> getWeekDays() {
        return weekDays;
    }

    public void setWeekDays(List<DayOfWeek> weekDays) {
        this.weekDays = weekDays;
    }

    public List<LocalTime> getHours() {
        return hours;
    }

    public void setHours(List<LocalTime> hours) {
        this.hours = hours;
    }

}
