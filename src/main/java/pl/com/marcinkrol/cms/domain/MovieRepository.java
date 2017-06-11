package pl.com.marcinkrol.cms.domain;

public interface MovieRepository {

    void put(Movie m);

    Movie get(Long id);

}
