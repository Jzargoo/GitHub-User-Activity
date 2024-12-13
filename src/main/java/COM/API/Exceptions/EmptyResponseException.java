package COM.API.Exceptions;

public class EmptyResponseException extends RuntimeException {
    public EmptyResponseException() {
        super("The resulting string is empty or null");
    }
}
