package pl.com.marcinkrol.cms.application;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalTime;

public class ShowingDto {

    private Long id;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime time;

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

}
