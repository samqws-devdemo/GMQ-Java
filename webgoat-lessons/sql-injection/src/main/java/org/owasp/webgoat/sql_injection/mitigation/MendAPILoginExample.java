package org.owasp.webgoat.sql_injection.mitigation;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class MendAPILoginExample {

    public static void main(String[] args) {
        String apiUrl = "https://api.example.com/auth"; // Replace with your actual API endpoint

        // Replace these with your actual credentials
        String username = "your_username";
        String password = "your_password";

        // Convert the request body to JSON format
        String requestBody = "{"
                + "\"username\":\"" + username + "\","
                + "\"password\":\"" + password + "\""
                + "}";

        // Create an HttpClient
        HttpClient httpClient = HttpClients.createDefault();

        // Build the request
        HttpPost request = new HttpPost(apiUrl);
        request.setHeader("Content-Type", "application/json");
        request.setEntity(new StringEntity(requestBody, "UTF-8"));

        try {
            // Send the request and get the response
            HttpResponse response = httpClient.execute(request);

            // Extract and print the response status code and body
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println("Response Code: " + statusCode);

            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuilder responseBody = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                responseBody.append(line);
            }
            System.out.println("Response Body: " + responseBody);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
