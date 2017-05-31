package pl.com.marcinkrol.cms.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Cinema {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String city;

    Cinema() {
    }

    public Cinema(CreateCinemaCommand cmd) {
        this.name = cmd.getName();
        this.city = cmd.getCity();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

}
