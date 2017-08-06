package pl.com.marcinkrol.cms.domain;

public interface CinemaRepository {

    void put(Cinema cinema);

    Cinema get(Long id);

    boolean exists(String name, String city);

}
