package COM;
import COM.commands.UserActivityCommand;

public class General {

    public static void main(String[] args) {
        if (args.length != 1) System.out.println("Usage: java -jar target/Github-user-activity-1.0-SNAPSHOT.jar <name>");
        else {
        UserActivityCommand user = new UserActivityCommand("Jzargoo");
        user.PresentUser();

        }
    }
}