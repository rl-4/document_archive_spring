package dhbw.demo.httpSearch;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class HttpSearch {
    public HttpResponse<String> search(URI uri) {

        try {
            HttpClient client = HttpClient.newBuilder()
                    .followRedirects(HttpClient.Redirect.NORMAL)
                    .build();

            HttpRequest request =
                    HttpRequest.newBuilder()
                            .GET()
                            .uri(uri)
                            .timeout(Duration.ofMinutes(1))
                            .header("Accept", "application/json")
                            .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
