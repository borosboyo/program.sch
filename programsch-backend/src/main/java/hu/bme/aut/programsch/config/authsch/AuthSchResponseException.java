package hu.bme.aut.programsch.config.authsch;

public class AuthSchResponseException extends RuntimeException {

    private static final long serialVersionUID = -3986811567199003859L;

    public AuthSchResponseException(String message) {
        super(message);
    }

    public AuthSchResponseException(String message, Throwable t) {
        super(message, t);
    }

}