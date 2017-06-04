package pl.com.marcinkrol.cms.integration;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class DbCleaner {

    private static final String TRUNCATE_SCHEMA_QUERY = "TRUNCATE SCHEMA PUBLIC RESTART IDENTITY AND COMMIT NO CHECK";

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void clean() {
        entityManager.createNativeQuery(TRUNCATE_SCHEMA_QUERY).executeUpdate();
    }

}
