package com.jobportal.jobportal.services.news;

import com.jobportal.jobportal.dtos.news.NewsResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class NewsService {
    private final WebClient webClient;

    @Value("${newsapi.url}")
    private String apiUrl;

    @Value("${newsapi.key}")
    private String apiKey;

    public NewsService(WebClient webClient) {
        this.webClient = webClient;
    }
    public Mono<NewsResponseDTO> fetchNews(String query) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .host("newsapi.org")
                        .path("/v2/everything")
                        .queryParam("q", query)
                        .queryParam("apiKey", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(NewsResponseDTO.class)
                .onErrorResume(e -> {
                    System.err.println("Błąd podczas pobierania newsów: " + e.getMessage());
                    return Mono.empty();
                });
    }
}
