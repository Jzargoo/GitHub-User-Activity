package COM.API.Exceptions;

public class RightsExceededException extends RuntimeException {
    public RightsExceededException() {
        super("Access rights are exceeded");
    }
}
