package br.com.nicolas.soundMusic.requests;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class DiscogsPublicApiExample {
    private final String key = System.getenv("discogs_key");
    private final String secret = System.getenv("discogs_secret");
    private final String userAgent = "ScreenMusicSounds/1.0";

    public void buscaArtista(String query) {
        try {
            String url = String.format(
                    "https://api.discogs.com/database/search?q=%s&type=artist&key=%s&secret=%s",
                    URLEncoder.encode(query, StandardCharsets.UTF_8),
                    URLEncoder.encode(key, StandardCharsets.UTF_8),
                    URLEncoder.encode(secret, StandardCharsets.UTF_8)
            );
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("User-Agent",this.userAgent)
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
