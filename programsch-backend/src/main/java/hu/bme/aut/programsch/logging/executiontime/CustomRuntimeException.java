package hu.bme.aut.programsch.logging.executiontime;

public class CustomRuntimeException extends RuntimeException {
    public CustomRuntimeException(Throwable errorMessage) {
        super(errorMessage);
    }
}
