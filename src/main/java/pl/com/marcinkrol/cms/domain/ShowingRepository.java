package pl.com.marcinkrol.cms.domain;

public interface ShowingRepository {

    void put(Showing showing);

    Showing get(Long showingId);

}
