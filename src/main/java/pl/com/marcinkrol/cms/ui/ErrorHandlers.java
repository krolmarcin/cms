package pl.com.marcinkrol.cms.ui;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.com.marcinkrol.cms.domain.InvalidActionException;
import pl.com.marcinkrol.cms.domain.InvalidCommandException;
import pl.com.marcinkrol.cms.domain.Validatable;

@ControllerAdvice
public class ErrorHandlers {

    private static final String APPLICATION_JSON = "application/json";

    @ExceptionHandler(InvalidCommandException.class)
    public ResponseEntity<Validatable.ValidationErrors> handleInvalidCommandException(InvalidCommandException ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON);
        return new ResponseEntity<>(
                ex.getErrors(),
                headers,
                HttpStatus.UNPROCESSABLE_ENTITY
        );
    }

    @ExceptionHandler(InvalidActionException.class)
    public ResponseEntity<String> handleInvalidActionException(InvalidActionException ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        return new ResponseEntity<>(
                String.format("{\"error\": \"%s\"}", ex.getMessage()),
                headers,
                HttpStatus.UNPROCESSABLE_ENTITY
        );
    }

}
