package COM.commands;
import COM.model.User;
import COM.API.GithubApiClient;


import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class UserActivityCommand {
    ArrayList<ArrayList<User>> arr;
    User[] users;
    String name;
    ObjectMapper mapper;

    public UserActivityCommand(String name){
        this.name = name;
    }

    User[] Deserialize(String n) {

        try {

            String json = GithubApiClient.getUserActivity(n).body();
            mapper = new ObjectMapper();
            return mapper.readValue(json,  User[].class);

        } catch (IOException | URISyntaxException | InterruptedException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    ArrayList<ArrayList<User>> groupEvents () {

        arr = new ArrayList<>();
        ArrayList<User> currentGroup = new ArrayList<>();

        for (User user: users) {

            if (currentGroup.isEmpty() ||
                    currentGroup.get(0).getType().equals(user.getType())) currentGroup.add(user);
            else {
                arr.add(currentGroup);
                currentGroup = new ArrayList<>();
                currentGroup.add(user);
            }

        }

        return arr;
    }

    public void PresentUser() {
        users = Deserialize(name);
        arr = groupEvents();
        for(ArrayList<User> user: arr) {
            switch (user.get(0).getType()) {

                case "PushEvent" -> System.out.println(" - Pushed commit(s) " +
                        (user.size() != 1? user.size(): " ") + " to " + user.get(0).getRepo().getName());

                default -> System.out.println(" - " + user.get(0).getType() +
                        (user.size() != 1? user.size(): "") + " to " + user.get(0).getRepo().getName());

            }
        }
    }
}