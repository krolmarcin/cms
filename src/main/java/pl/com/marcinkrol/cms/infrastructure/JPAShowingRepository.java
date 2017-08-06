package pl.com.marcinkrol.cms.infrastructure;

import pl.com.marcinkrol.cms.domain.EntityNotFoundException;
import pl.com.marcinkrol.cms.domain.Showing;
import pl.com.marcinkrol.cms.domain.ShowingRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

}
