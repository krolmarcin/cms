package pl.com.marcinkrol.cms.infrastructure;

import pl.com.marcinkrol.cms.application.CinemaCatalog;
import pl.com.marcinkrol.cms.application.CinemaDto;
import pl.com.marcinkrol.cms.domain.Cinema;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.LinkedList;
import java.util.List;

public class JPACinemaCatalog implements CinemaCatalog {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CinemaDto> getCinemas() {
        String queryGetCinemas = "FROM Cinema c";
        Query query = entityManager.createQuery(queryGetCinemas);
        List<Cinema> cinemas = query.getResultList();
        List<CinemaDto> cinemaDtos = new LinkedList<>();
        for (Cinema cinema : cinemas) {
            cinemaDtos.add(getCinemaDtos(cinema));
        }
        return cinemaDtos;
    }

    private CinemaDto getCinemaDtos(Cinema cinema) {
        CinemaDto cinemaDto = new CinemaDto();
        cinemaDto.setId(cinema.getId());
        cinemaDto.setName(cinema.getName());
        cinemaDto.setCity(cinema.getCity());
        return cinemaDto;
    }

}
