package com.jobportal.jobportal.services.geocoding;

import com.jobportal.jobportal.dtos.geocoding.GeocodingResponseDTO;
import reactor.core.publisher.Mono;

public interface GeocodingService {

    Mono<GeocodingResponseDTO> fetchCoordinates(String address);

}
