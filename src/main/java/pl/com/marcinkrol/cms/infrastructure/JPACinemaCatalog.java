package pl.com.marcinkrol.cms.infrastructure;

import pl.com.marcinkrol.cms.application.*;
import pl.com.marcinkrol.cms.domain.Cinema;
import pl.com.marcinkrol.cms.domain.Movie;
import pl.com.marcinkrol.cms.domain.Showing;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.Comparator;
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

    @Override
    public List<MovieShowingsDto> getShowings(Long cinemaId, LocalDate date) {
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

    private MovieShowingsDto createMovieShowingsDto(Movie movie) {
        MovieShowingsDto movieShowingsDto = new MovieShowingsDto();
        movieShowingsDto.setMovie(createMovieDto(movie));
        movieShowingsDto.setShows(createShowingDto(movie));
        return movieShowingsDto;
    }

    private List<ShowingDto> createShowingDto(Movie movie) {
        List<ShowingDto> showingDtos = new LinkedList<>();
        for (Showing showing : movie.getShowings()) {
            ShowingDto showingDto = new ShowingDto();
            showingDto.setId(showing.getId());
            showingDto.setTime(showing.getBeginsAt().toLocalTime());
            showingDtos.add(showingDto);
            showingDtos.sort(new Comparator<ShowingDto>() {
                @Override
                public int compare(ShowingDto o1, ShowingDto o2) {
                    return o1.getTime().compareTo(o2.getTime());
                }
            });
        }
        return showingDtos;
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

    private CinemaDto getCinemaDtos(Cinema cinema) {
        CinemaDto cinemaDto = new CinemaDto();
        cinemaDto.setId(cinema.getId());
        cinemaDto.setName(cinema.getName());
        cinemaDto.setCity(cinema.getCity());
        return cinemaDto;
    }

}
