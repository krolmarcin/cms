package pl.com.marcinkrol.cms.infrastructure;

import pl.com.marcinkrol.cms.domain.EntityNotFoundException;
import pl.com.marcinkrol.cms.domain.Movie;
import pl.com.marcinkrol.cms.domain.MovieRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class JPAMovieRepository implements MovieRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void put(Movie m) {
        entityManager.persist(m);
    }

    @Override
    public Movie get(Long id) {
        Movie movie = entityManager.find(Movie.class, id);
        if (movie == null)
            throw new EntityNotFoundException("movie", id);
        return movie;
    }

}
