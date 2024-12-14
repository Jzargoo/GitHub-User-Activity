package COM.API.Exceptions;

public class BadConnectionException extends RuntimeException {
    public BadConnectionException() {
        super("Trouble with connection github");
    }
}
