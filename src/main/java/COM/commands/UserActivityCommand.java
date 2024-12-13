package COM.commands;
import COM.API.Exceptions.UncorrectEvent;
import COM.model.User;
import COM.API.GithubApiClient;


import com.fasterxml.jackson.databind.ObjectMapper;
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

        try {

            String json = GithubApiClient.getUserActivity(n).body();
            mapper = new ObjectMapper();
            return mapper.readValue(json, User[].class);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    ArrayList<ArrayList<User>> groupEvents() {

        arr = new ArrayList<>();
        ArrayList<User> currentGroup = new ArrayList<>();

        for (User user : users) {

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

        for (ArrayList<User> userGroup : arr) {

            User user = userGroup.get(0);
            String eventType = user.getType();
            int eventCount = userGroup.size();

            switch (eventType) {

                case "CommitCommentEvent" -> {
                    System.out.println("--- Added " + (eventCount > 1 ? eventCount : "") +
                            " commit comment(s) in repository " + user.getRepo().getName());
                }

                case "CreateEvent" -> {
                    System.out.println("--- Created a new resource in repository: " + user.getRepo().getName());
                }

                case "DeleteEvent" -> {
                    System.out.println("--- Deleted a resource in repository: " + user.getRepo().getName());
                }

                case "ForkEvent" -> {
                    System.out.println("--- Forked repository " + user.getRepo().getName() +
                            " to another location.");
                }

                case "GollumEvent" -> {
                    System.out.println("--- Updated a wiki page in repository " + user.getRepo().getName());
                }

                case "IssueCommentEvent" -> {
                    System.out.println("--- Added " + (eventCount > 1 ? eventCount : "") +
                            " comment(s) to issues in repository " + user.getRepo().getName());
                }

                case "IssuesEvent" -> {
                    System.out.println("--- Opened/closed " + (eventCount > 1 ? eventCount : "") +
                            " issue(s) in repository " + user.getRepo().getName());
                }

                case "MemberEvent" -> {
                    System.out.println("--- Added a new member to the repository: " + user.getRepo().getName());
                }

                case "PublicEvent" -> {
                    System.out.println("--- Made repository " + user.getRepo().getName() + " public.");
                }

                case "PullRequestEvent" -> {
                    System.out.println("--- Created " + (eventCount > 1 ? eventCount : "") +
                            " pull request(s) in repository " + user.getRepo().getName());
                }

                case "PullRequestReviewEvent" -> {
                    System.out.println("--- Reviewed " + (eventCount > 1 ? eventCount : "") +
                            " pull request(s) in repository " + user.getRepo().getName());
                }

                case "PullRequestReviewCommentEvent" -> {
                    System.out.println("--- Added " + (eventCount > 1 ? eventCount : "") +
                            " review comment(s) to pull request(s) in repository " + user.getRepo().getName());
                }

                case "PullRequestReviewThreadEvent" -> {
                    System.out.println("--- Added review thread(s) in repository " + user.getRepo().getName());
                }

                case "PushEvent" -> {
                    System.out.println("--- Pushed " + (eventCount > 1 ? eventCount : "") +
                            " commit(s) to repository " + user.getRepo().getName());
                }

                case "ReleaseEvent" -> {
                    System.out.println("--- Released new version(s) in repository " + user.getRepo().getName());
                }

                case "SponsorshipEvent" -> {
                    System.out.println("--- Managed sponsorship(s) for repository " + user.getRepo().getName());
                }

                case "WatchEvent" -> {
                    System.out.println("--- Watched repository " + user.getRepo().getName());
                }

                default -> {
                    throw new UncorrectEvent();
                }

            }
        }
    }
}