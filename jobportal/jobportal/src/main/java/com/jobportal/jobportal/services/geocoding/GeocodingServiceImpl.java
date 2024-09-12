package com.jobportal.jobportal.services.geocoding;

import com.jobportal.jobportal.dtos.geocoding.GeocodingResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class GeocodingServiceImpl implements GeocodingService{

    private final WebClient webClient;

    @Value("${geocodingapi.key}")
    private String apiKey;

    public GeocodingServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<GeocodingResponseDTO> fetchCoordinates(String address) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .host("maps.googleapis.com")
                        .path("/maps/api/geocode/json")
                        .queryParam("address", address.replace(" ", "+"))
                        .queryParam("key", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(GeocodingResponseDTO.class)
                .onErrorResume(e -> {
                    System.err.println("Error while fetching coordinates: " + e.getMessage());
                    return Mono.empty();
                });
    }
}
