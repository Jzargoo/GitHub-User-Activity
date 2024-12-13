package COM.API.Exceptions;

public class UncorrectEvent extends RuntimeException {
    public UncorrectEvent() {
        super("The action you received is missing in the GitHub version");
    }
}
