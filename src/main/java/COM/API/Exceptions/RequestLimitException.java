package COM.API.Exceptions;

public class RequestLimitException extends RuntimeException {
    public RequestLimitException() {
        super("The number of requests exceeded is exceeded after a while");
    }
}
