package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetWayController {
    private final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(3))
            .build();

    @GetMapping("/api/user/**")
    public ResponseEntity<String> proxy(HttpServletRequest incomingRequest)
            throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder(URI.create(target(incomingRequest)))
                .timeout(Duration.ofSeconds(10))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return ResponseEntity.status(response.statusCode()).body(response.body());
    }

    @PostMapping("/api/user/**")
    public ResponseEntity<String> proxyPost(
            HttpServletRequest incomingRequest,
            @RequestBody String requestBody,
            @RequestHeader(value = "Content-Type", required = false) String contentType)
            throws IOException, InterruptedException {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder(URI.create(target(incomingRequest)))
                .timeout(Duration.ofSeconds(10))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody));
        if (contentType != null) {
            requestBuilder.header("Content-Type", contentType);
        }

        HttpResponse<String> response = client.send(requestBuilder.build(), HttpResponse.BodyHandlers.ofString());
        return ResponseEntity.status(response.statusCode()).body(response.body());
    }

    @DeleteMapping("/api/user/**")
    public ResponseEntity<String> proxyDelete(HttpServletRequest incomingRequest)
            throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder(URI.create(target(incomingRequest)))
                .timeout(Duration.ofSeconds(10))
                .DELETE()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return ResponseEntity.status(response.statusCode()).body(response.body());
    }

    private String target(HttpServletRequest incomingRequest) {
        String downstreamPath = incomingRequest.getRequestURI().substring("/api/user".length());
        String query = incomingRequest.getQueryString();
        return "http://localhost:8080" + downstreamPath
                + (query == null ? "" : "?" + query);
    }
}
