package pl.com.marcinkrol.cms.infrastructure;

import pl.com.marcinkrol.cms.domain.EntityNotFoundException;
import pl.com.marcinkrol.cms.domain.Showing;
import pl.com.marcinkrol.cms.domain.ShowingRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class JPAShowingRepository implements ShowingRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void put(Showing showing) {
        entityManager.persist(showing);
    }

    @Override
    public Showing get(Long showingId) {
        Showing showing = entityManager.find(Showing.class, showingId);
        if (showing == null)
            throw new EntityNotFoundException("showing", showingId);
        return showing;
    }

    @Override
    public boolean isAlreadyAdded(Showing showing) {
        Query query = entityManager.createQuery("Select s FROM Showing s WHERE s.cinema = :cinema AND s.movie = :movie AND s.beginsAt = :beginsAt");
        query.setParameter("cinema", showing.getCinema());
        query.setParameter("movie", showing.getMovie());
        query.setParameter("beginsAt", showing.getBeginsAt());
        return !query.getResultList().isEmpty();
    }

}
