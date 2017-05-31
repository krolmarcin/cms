package pl.com.marcinkrol.cms.infrastructure;

import pl.com.marcinkrol.cms.domain.Cinema;
import pl.com.marcinkrol.cms.domain.CinemaRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class JPACinemaRepository implements CinemaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void put(Cinema cinema) {
        entityManager.persist(cinema);
    }

}
