package COM.API.Exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User with this name doesn't exist");
    }
}
