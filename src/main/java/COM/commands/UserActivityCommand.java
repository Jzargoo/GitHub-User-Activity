package COM.commands;
import COM.API.Exceptions.EmptyResponseException;
import COM.API.Exceptions.UncorrectEvent;
import COM.model.User;
import COM.API.GithubApiClient;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class UserActivityCommand {
    ArrayList<ArrayList<User>> arr;
    User[] users;
    String name;
    ObjectMapper mapper;

    public UserActivityCommand(String name) {
        this.name = name;
    }

    User[] Deserialize(String n) {

        String json;
        try {
            json = GithubApiClient.getUserActivity(n).body();
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        mapper = new ObjectMapper();
        if(json == null || json.length() < 3) throw  new EmptyResponseException();
        try {
            return mapper.readValue(json, User[].class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    ArrayList<ArrayList<User>> groupEvents() {

        arr = new ArrayList<>();
        ArrayList<User> currentGroup = new ArrayList<>();

        for (User user : users) {

            if (currentGroup.isEmpty() ||
                    (currentGroup.get(0).getType().equals(user.getType()) &&
                            currentGroup.get(0).getRepo().getId().equals(user.getRepo().getId()))) currentGroup.add(user);
            else {
                arr.add(currentGroup);
                currentGroup = new ArrayList<>();
                currentGroup.add(user);
            }

        }
        arr.add(currentGroup);
        return arr;
    }

    public void PresentUser() {

        try{

            users = Deserialize(name);
            arr = groupEvents();

        } catch (EmptyResponseException e) {
            System.out.println(e.getMessage());
        }

        for(ArrayList<User> user: arr) {

            switch (user.get(0).getType()) {

                case "CommitCommentEvent" ->
                    System.out.println("- Added " + (user.size() > 1 ? user.size() : "") +
                            " commit comment(s) in repository " + user.get(0).getRepo().getName());


                case "CreateEvent" ->
                    System.out.println("- Created a new resource in repository: " + user.get(0).getRepo().getName());


                case "DeleteEvent" ->
                    System.out.println("- Deleted a resource in repository: " + user.get(0).getRepo().getName());

                case "ForkEvent" ->
                    System.out.println("- Forked repository " + user.get(0).getRepo().getName() + " to another location.");


                case "GollumEvent" ->
                        System.out.println("- Updated a wiki page in repository " + user.get(0).getRepo().getName());


                case "IssueCommentEvent" ->
                    System.out.println("- Added " + (user.size() > 1 ? user.size() : "") +
                            " comment(s) to issues in repository " + user.get(0).getRepo().getName());


                case "IssuesEvent" ->
                    System.out.println("- Opened/closed " + (user.size() > 1 ? user.size() : "") +
                            " issue(s) in repository " + user.get(0).getRepo().getName());


                case "MemberEvent" ->
                    System.out.println("- Added a new member to the repository: " + user.get(0).getRepo().getName());


                case "PublicEvent" ->
                    System.out.println("- Made repository " + user.get(0).getRepo().getName() + " public.");


                case "PullRequestEvent" ->
                    System.out.println("- Created " + (user.size() > 1 ? user.size() : "") +
                            " pull request(s) in repository " + user.get(0).getRepo().getName());


                case "PullRequestReviewEvent" ->
                    System.out.println("- Reviewed " + (user.size() > 1 ? user.size() : "") +
                            " pull request(s) in repository " + user.get(0).getRepo().getName());

                case "PullRequestReviewCommentEvent" ->
                    System.out.println("- Added " + (user.size() > 1 ? user.size() : "") +
                            " review comment(s) to pull request(s) in repository " + user.get(0).getRepo().getName());

                case "PullRequestReviewThreadEvent" ->
                    System.out.println("- Added review thread(s) in repository " + user.get(0).getRepo().getName());

                case "PushEvent" ->
                    System.out.println("- Pushed " + (user.size() > 1 ? user.size() : "") +
                            " commit(s) to repository " + user.get(0).getRepo().getName());

                case "ReleaseEvent" ->
                    System.out.println("- Released new version(s) in repository " + user.get(0).getRepo().getName());

                case "SponsorshipEvent" ->
                    System.out.println("- Managed sponsorship(s) for repository " + user.get(0).getRepo().getName());

                case "WatchEvent" ->
                    System.out.println("- Watched repository " + user.get(0).getRepo().getName());

                default ->
                    throw new UncorrectEvent();

            }
        }
    }
}
