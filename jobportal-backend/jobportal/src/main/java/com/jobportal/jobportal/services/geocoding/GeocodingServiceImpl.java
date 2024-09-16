package com.jobportal.jobportal.services.geocoding;

import com.jobportal.jobportal.dtos.geocoding.CoordinatesDTO;
import com.jobportal.jobportal.dtos.geocoding.GeocodingResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class GeocodingServiceImpl implements GeocodingService {

    private final WebClient webClient;

    @Value("${geocodingapi.key}")
    private String apiKey;

    public GeocodingServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    public CoordinatesDTO fetchCoordinates(String address) {
        GeocodingResponseDTO response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .host("maps.googleapis.com")
                        .path("/maps/api/geocode/json")
                        .queryParam("address", address.replace(" ", "+"))
                        .queryParam("key", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(GeocodingResponseDTO.class)
                .block();

        if (response != null && !response.getResults().isEmpty()) {
            Double lat = response.getResults().getFirst().getGeometry().getLocation().getLat();
            Double lng = response.getResults().getFirst().getGeometry().getLocation().getLng();
            return new CoordinatesDTO(lat, lng);
        }

        // Loguj odpowiedź w przypadku problemów
        System.out.println("Geocoding API zwróciło pustą odpowiedź lub brak wyników.");
        return new CoordinatesDTO(0.0, 0.0);
    }

}
