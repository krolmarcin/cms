package pl.com.marcinkrol.cms.domain;

import java.util.*;

public interface Validatable {

    String REQUIRED_FIELD = "is a required field and can not be null";
    String FUTURE_DATE_REQUIRED = "must be in the futute";
    String NON_NULL_ELEMENT = "is a required field and can not contain empty elements";



    void validate(ValidationErrors errors);

    default boolean isEmpty(Object o) {
        return (o == null || o.toString().trim().isEmpty());
    }

    default boolean isEmpty(Collection c) {
        return (c == null || c.toString().trim().isEmpty() || c.size() == 0) || ensureNotEmptyValue(c);
    }

    default boolean ensureNotEmptyValue(Collection c){
        for (Object o : c){
            return (isEmpty(o));
        }
        return false;
    }

    class ValidationErrors {

        private Map<String, Set<String>> errors = new HashMap<>();

        public void add(String fieldName, String errorMessage) {
            Set<String> fieldErrors = errors.getOrDefault(fieldName, new HashSet<>());
            fieldErrors.add(errorMessage);
            errors.putIfAbsent(fieldName, fieldErrors);
        }

        public boolean isValid() {
            return errors.isEmpty();
        }

        public Map<String, Set<String>> getErrors() {
            return new HashMap<>(errors);
        }

    }

    default boolean isEmpty(String s) {
        return s == null || s.trim().length() == 0;
    }

}
