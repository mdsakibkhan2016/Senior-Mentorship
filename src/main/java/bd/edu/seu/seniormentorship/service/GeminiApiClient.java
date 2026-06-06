package bd.edu.seu.seniormentorship.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * Low-level client that calls the Google Gemini REST API directly.
 * No Spring AI dependency required — uses JDK 21 HttpClient.
 */
@Service
public class GeminiApiClient {

    private static final Logger log = LoggerFactory.getLogger(GeminiApiClient.class);

    private static final String BASE_URL =
        "https://generativelanguage.googleapis.com/v1beta/models/%s:generateContent?key=%s";

    @Value("${gemini.api.key}")
    private String apiKey;

    @Value("${gemini.api.model:gemini-1.5-flash}")
    private String model;

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public GeminiApiClient() {
        this.httpClient  = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(30))
                .build();
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Send a plain text prompt to Gemini and return the text response.
     */
    public String chat(String prompt) throws Exception {
        String url = String.format(BASE_URL, model.trim(), apiKey.trim());

        // Build request body
        String requestBody = objectMapper.writeValueAsString(
            objectMapper.createObjectNode()
                .set("contents", objectMapper.createArrayNode()
                    .add(objectMapper.createObjectNode()
                        .set("parts", objectMapper.createArrayNode()
                            .add(objectMapper.createObjectNode()
                                .put("text", prompt)))))
        );

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .timeout(Duration.ofSeconds(60))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response =
                httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        log.debug("Gemini HTTP status: {}", response.statusCode());
        log.debug("Gemini raw response: {}", response.body());

        if (response.statusCode() != 200) {
            throw new RuntimeException("Gemini API error " + response.statusCode()
                    + ": " + response.body());
        }

        // Parse: candidates[0].content.parts[0].text
        JsonNode root = objectMapper.readTree(response.body());
        return root.path("candidates")
                   .path(0)
                   .path("content")
                   .path("parts")
                   .path(0)
                   .path("text")
                   .asText("");
    }
}
