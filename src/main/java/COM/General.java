package COM;
import COM.commands.UserActivityCommand;

public class General {

    public static void main(String[] args) {
        UserActivityCommand user = new UserActivityCommand("kamranahmedse");
        user.PresentUser();
    }
}