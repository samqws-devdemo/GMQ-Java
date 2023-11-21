package org.owasp.webgoat.sql_injection.mitigation;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

public class MendGetVulns {

    public static void main(String[] args) {
        String apiUrl = "https://api.example.com"; // Replace with your actual API base URL
        String endpoint = "/v1/organization/vulnerable-libs-count-by-projects";

        // Replace these with your actual Mend API credentials
        String apiKey = "your_api_key";
        String apiSecret = "your_api_secret";

        // Combine API key and secret for Basic Authentication
        String credentials = apiKey + ":" + apiSecret;
        String basicAuth = "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());

        // Create an HttpClient
        HttpClient httpClient = HttpClient.newHttpClient();

        // Build the request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl + endpoint))
                .header("Authorization", basicAuth)
                .GET()
                .build();

        try {
            // Send the request and get the response
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // Print the response status code and body
            System.out.println("Response Code: " + response.statusCode());
            System.out.println("Response Body: " + response.body());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
