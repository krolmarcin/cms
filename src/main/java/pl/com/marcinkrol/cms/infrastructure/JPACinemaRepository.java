package pl.com.marcinkrol.cms.infrastructure;

import pl.com.marcinkrol.cms.domain.Cinema;
import pl.com.marcinkrol.cms.domain.CinemaRepository;
import pl.com.marcinkrol.cms.domain.EntityNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class JPACinemaRepository implements CinemaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void put(Cinema cinema) {
        entityManager.persist(cinema);
    }

    @Override
    public Cinema get(Long id) {
        Cinema cinema = entityManager.find(Cinema.class, id);
        if (cinema == null)
            throw new EntityNotFoundException("cinema", id);
        return cinema;
    }

    @Override
    public boolean exists(String name, String city) {
        String queryMessage = "FROM Cinema c WHERE c.name =:name AND c.city =:city";
        Query query = entityManager.createQuery(queryMessage);
        query.setParameter("name", name);
        query.setParameter("city", city);
        return (!query.getResultList().isEmpty());
    }

}
