package pl.com.marcinkrol.cms.infrastructure;

import pl.com.marcinkrol.cms.domain.Pricing;
import pl.com.marcinkrol.cms.domain.PricingRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class JPAPricingRepository implements PricingRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void put(Pricing p) {
        entityManager.persist(p);
    }

}
