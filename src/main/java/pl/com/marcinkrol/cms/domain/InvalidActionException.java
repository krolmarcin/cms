package pl.com.marcinkrol.cms.domain;

public class InvalidActionException extends RuntimeException {

    public InvalidActionException(String msg) {
        super(msg);
    }

}
