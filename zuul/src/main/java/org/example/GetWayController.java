package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetWayController {
    private final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(3))
            .build();

    @GetMapping("/api/user/**")
    public ResponseEntity<String> proxy(HttpServletRequest incomingRequest)
            throws IOException, InterruptedException {
        String downstreamPath = incomingRequest.getRequestURI().substring("/api/user".length());
        String query = incomingRequest.getQueryString();
        String target = "http://localhost:8080" + downstreamPath
                + (query == null ? "" : "?" + query);

        HttpRequest request = HttpRequest.newBuilder(URI.create(target))
                .timeout(Duration.ofSeconds(10))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return ResponseEntity.status(response.statusCode()).body(response.body());
    }
}
