package pl.com.marcinkrol.cms.domain;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String entityName, Long id) {
        super(String.format("%s with id %s does not exist", entityName, id));
    }

    public EntityNotFoundException(String entityName, String id) {
        super(String.format("%s with id %s does not exist", entityName, id));
    }

}
