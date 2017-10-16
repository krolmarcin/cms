package pl.com.marcinkrol.cms.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class ShowingCalendar implements Validatable {

    private static final DateTimeFormatter CORRECT_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd kk:mm");
    private static final DateTimeFormatter CORRECT_TIME_FORMAT = DateTimeFormatter.ofPattern("kk:mm");

    private String fromDate;
    private String untilDate;

    private List<String> weekDays;
    private List<String> hours;

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getUntilDate() {
        return untilDate;
    }

    public void setUntilDate(String untilDate) {
        this.untilDate = untilDate;
    }

    public List<String> getWeekDays() {
        return weekDays;
    }

    public void setWeekDays(List<String> weekDays) {
        this.weekDays = weekDays;
    }

    public List<String> getHours() {
        return hours;
    }

    public void setHours(List<String> hours) {
        this.hours = hours;
    }

    @Override
    public void validate(ValidationErrors errors) {
        validateFromDate(errors);
        validateUntilDate(errors);
        validateFromDateIsBeforeUntilDate(errors);
    }

    private void validateFromDateIsBeforeUntilDate(ValidationErrors errors) {
        if ((!isCorrectFormat(fromDate)) && (!isCorrectFormat(untilDate)) && isBefore(fromDate, untilDate))
            errors.add("date", "fromDate cannot be before than untilDate");
    }

    private void validateFromDate(ValidationErrors errors) {
        if (fromDate == null)
            errors.add("fromDate", REQUIRED_FIELD);
        else if (isCorrectFormat(fromDate))
            errors.add("fromDate", INCORRECT_DATE_FORMAT);
        else if (isBeforeNow(fromDate))
            errors.add("fromDate", FUTURE_DATE_REQUIRED);
    }

    private void validateUntilDate(ValidationErrors errors) {
        if (untilDate == null)
            errors.add("untilDate", REQUIRED_FIELD);
        else if (isCorrectFormat(untilDate))
            errors.add("untilDate", INCORRECT_DATE_FORMAT);
        else if (isBeforeNow(untilDate))
            errors.add("untilDate", FUTURE_DATE_REQUIRED);
    }

    private boolean isBefore(String fromDate, String untilDate) {
        LocalDateTime from = LocalDateTime.parse(fromDate, CORRECT_DATE_TIME_FORMAT);
        LocalDateTime until = LocalDateTime.parse(untilDate, CORRECT_DATE_TIME_FORMAT);
        return until.isBefore(from);
    }

    private boolean isBeforeNow(String date) {
        LocalDateTime dateParsed = LocalDateTime.parse(date, CORRECT_DATE_TIME_FORMAT);
        return (dateParsed.isBefore(LocalDateTime.now()));
    }

    private boolean isCorrectFormat(String date) {
        boolean result;
        try {
            LocalDateTime.parse(date, CORRECT_DATE_TIME_FORMAT);
            result = false;
        } catch (DateTimeParseException e) {
            e.getMessage();
            result = true;
        }
        return result;
    }

}
