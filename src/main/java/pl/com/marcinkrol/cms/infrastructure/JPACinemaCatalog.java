package pl.com.marcinkrol.cms.infrastructure;

import pl.com.marcinkrol.cms.application.*;
import pl.com.marcinkrol.cms.domain.Cinema;
import pl.com.marcinkrol.cms.domain.InvalidActionException;
import pl.com.marcinkrol.cms.domain.Movie;
import pl.com.marcinkrol.cms.domain.Showing;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.*;

public class JPACinemaCatalog implements CinemaCatalog {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CinemaDto> getCinemas() {
        String queryGetCinemas = "FROM Cinema c";
        Query query = entityManager.createQuery(queryGetCinemas);
        List<Cinema> cinemas = query.getResultList();
        return createCinemaDtos(cinemas);
    }

    @Override
    public List<MovieShowingsDto> getShowings(Long cinemaId, LocalDate date) {
        ensureDateNotNull(date);

        List<MovieShowingsDto> movieShowingsDtos = new LinkedList<>();
        String queryGetShowingsFromCinemaOnDate = "SELECT DISTINCT m FROM Movie m " +
                "LEFT JOIN FETCH m.showings s " +
                "LEFT JOIN FETCH s.cinema c " +
                "WHERE c.id = :cinemaId " +
                "AND s.beginsAt BETWEEN :startHourOfDay AND :endHourOfDay " +
                "ORDER BY m.title ASC";
        Query query = entityManager.createQuery(queryGetShowingsFromCinemaOnDate);
        query.setParameter("cinemaId", cinemaId);
        query.setParameter("startHourOfDay", date.atStartOfDay());
        query.setParameter("endHourOfDay", date.atStartOfDay().plusDays(1));

        List<Movie> movies = query.getResultList();
        for (Movie movie : movies) {
            movieShowingsDtos.add(createMovieShowingsDto(movie));
        }
        return movieShowingsDtos;
    }

    private void ensureDateNotNull(LocalDate date) {
        if (date == null)
            throw new InvalidActionException("Date is missing");
    }

    private MovieShowingsDto createMovieShowingsDto(Movie movie) {
        MovieShowingsDto movieShowingsDto = new MovieShowingsDto();
        movieShowingsDto.setMovie(createMovieDto(movie));
        movieShowingsDto.setShows(createMovieShows(movie.getShowings()));
        return movieShowingsDto;
    }

    private List<Show> createMovieShows(Set<Showing> showings) {
        List<Show> shows = new LinkedList<>();
        for (Showing showing : showings) {
            shows.add(new Show(showing.getId(), showing.getBeginsAt().toLocalTime()));
        }
        Collections.sort(shows);
        return shows;
    }

    private MovieDto createMovieDto(Movie movie) {
        MovieDto movieDto = new MovieDto();
        movieDto.setTitle(movie.getTitle());
        movieDto.setDescription(movie.getDescription());
        movieDto.setActors(movie.getActors());
        movieDto.setGenres(movie.getGenres());
        movieDto.setMinAge(movie.getMinAge());
        movieDto.setLength(movie.getLength());
        return movieDto;
    }

    private List<CinemaDto> createCinemaDtos(List<Cinema> cinemas) {
        List<CinemaDto> cinemaDtos = new LinkedList<>();
        for (Cinema cinema : cinemas) {
            cinemaDtos.add(createCinemaDtos(cinema));
        }
        return cinemaDtos;
    }

    private CinemaDto createCinemaDtos(Cinema cinema) {
        CinemaDto cinemaDto = new CinemaDto();
        cinemaDto.setId(cinema.getId());
        cinemaDto.setName(cinema.getName());
        cinemaDto.setCity(cinema.getCity());
        return cinemaDto;
    }

}
