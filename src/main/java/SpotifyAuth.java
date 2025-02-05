import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class SpotifyAuth {
    private static final String CLIENT_ID = "your_client_id";
    private static final String REDIRECT_URI = "http://localhost:8888/callback";
    private static final String TOKEN_URL = "https://accounts.spotify.com/api/token";

    public static String getAccessToken() throws IOException, InterruptedException {
        System.out.println("Go to the following URL to authenticate:");
        System.out.println("https://accounts.spotify.com/authorize?client_id=" + CLIENT_ID +
                           "&response_type=code&redirect_uri=" + REDIRECT_URI +
                           "&scope=playlist-modify-public playlist-modify-private");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the authorization code:");
        String authCode = scanner.nextLine();

        return requestAccessToken(authCode);
    }

    private static String requestAccessToken(String authCode) throws IOException, InterruptedException {
        String body = "client_id=" + CLIENT_ID +
                      "&grant_type=authorization_code" +
                      "&code=" + authCode +
                      "&redirect_uri=" + REDIRECT_URI;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(TOKEN_URL))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Extract access token from response JSON
        String jsonResponse = response.body();
        String accessToken = jsonResponse.split("\"access_token\":\"")[1].split("\"")[0];

        storeAccessToken(accessToken);
        return accessToken;
    }

    private static void storeAccessToken(String token) throws IOException {
        Files.writeString(Path.of("access_token.txt"), token);
    }

    public static String getStoredAccessToken() throws IOException {
        return Files.readString(Path.of("access_token.txt")).trim();
    }
}

/*
 import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Base64;

public class SpotifyAuth {
    private static final String CLIENT_ID = "your_client_id"; // Replace with your Spotify Client ID
    private static final String CLIENT_SECRET = "your_client_secret"; // Replace with your Spotify Client Secret
    private static final String REDIRECT_URI = "http://localhost:8888/callback";
    private static final String TOKEN_FILE = "access_token.txt";

    // Step 1: Direct user to authenticate
    public static void authenticateUser() {
        String authURL = "https://accounts.spotify.com/authorize"
                + "?client_id=" + CLIENT_ID
                + "&response_type=code"
                + "&redirect_uri=" + REDIRECT_URI
                + "&scope=user-read-private%20user-read-email%20playlist-modify-public%20playlist-modify-private";

        System.out.println("Open this URL in your browser to authenticate:\n" + authURL);
        System.out.println("After granting access, enter the authorization code here:");
        
        try (Scanner scanner = new Scanner(System.in)) {
            String authCode = scanner.nextLine();
            fetchAccessToken(authCode);
        } catch (IOException e) {
            System.err.println("Error obtaining access token: " + e.getMessage());
        }
    }

    // Step 2: Exchange Authorization Code for Access Token
    private static void fetchAccessToken(String authCode) throws IOException {
        URL url = new URL("https://accounts.spotify.com/api/token");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("Authorization", "Basic " + getBase64EncodedCredentials());

        String requestBody = "grant_type=authorization_code"
                + "&code=" + authCode
                + "&redirect_uri=" + REDIRECT_URI;

        connection.getOutputStream().write(requestBody.getBytes());

        Scanner scanner = new Scanner(connection.getInputStream()).useDelimiter("\\A");
        String response = scanner.hasNext() ? scanner.next() : "";
        scanner.close();

        String accessToken = extractToken(response, "access_token");
        storeAccessToken(accessToken);
    }

    // Step 3: Store the Access Token
    private static void storeAccessToken(String accessToken) throws IOException {
        try (PrintWriter writer = new PrintWriter(TOKEN_FILE)) {
            writer.println(accessToken);
        }
        System.out.println("Access token stored successfully.");
    }

    // Step 4: Retrieve the Access Token
    public static String getStoredAccessToken() throws IOException {
        Path tokenPath = Paths.get(TOKEN_FILE);

        if (!Files.exists(tokenPath)) {
            throw new IOException("Access token file not found. Please authenticate using `java Main auth`.");
        }

        String token = Files.readString(tokenPath).trim();

        if (token.isEmpty()) {
            throw new IOException("Access token file is empty. Please re-authenticate.");
        }

        return token;
    }

    // Step 5: Handle Token Refresh (if needed)
    public static String refreshAccessToken(String refreshToken) throws IOException {
        URL url = new URL("https://accounts.spotify.com/api/token");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("Authorization", "Basic " + getBase64EncodedCredentials());

        String requestBody = "grant_type=refresh_token&refresh_token=" + refreshToken;
        connection.getOutputStream().write(requestBody.getBytes());

        Scanner scanner = new Scanner(connection.getInputStream()).useDelimiter("\\A");
        String response = scanner.hasNext() ? scanner.next() : "";
        scanner.close();

        return extractToken(response, "access_token");
    }

    // Utility: Extract Token from JSON Response
    private static String extractToken(String jsonResponse, String key) {
        String[] pairs = jsonResponse.replace("{", "").replace("}", "").split(",");
        for (String pair : pairs) {
            String[] keyValue = pair.split(":");
            if (keyValue[0].trim().contains(key)) {
                return keyValue[1].replaceAll("\"", "").trim();
            }
        }
        return null;
    }

    // Utility: Base64 Encode Client Credentials
    private static String getBase64EncodedCredentials() {
        String credentials = CLIENT_ID + ":" + CLIENT_SECRET;
        return Base64.getEncoder().encodeToString(credentials.getBytes());
    }
}

 */