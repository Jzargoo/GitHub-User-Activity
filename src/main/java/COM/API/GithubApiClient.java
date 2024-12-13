package COM.API;

import COM.API.Exceptions.RequestLimitException;
import COM.API.Exceptions.RightsExceededException;
import COM.API.Exceptions.UserNotFoundException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GithubApiClient {

    public static HttpResponse<String> getUserActivity(String name) throws URISyntaxException, IOException, InterruptedException {

        String url = "https://api.github.com/users/" + name + "/events";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .header("User-Agent", "JavaHttpClient")
                .GET()
                .build();
        HttpResponse<String> send = client.send(request, HttpResponse.BodyHandlers.ofString());
        switch (send.statusCode()){
            case 404 -> throw new UserNotFoundException();
            case 403 -> throw new RequestLimitException();
            case 401 -> throw new RightsExceededException();
            default -> {
                return send;
            }

        }
    }
}
