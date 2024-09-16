package com.jobportal.jobportal.services.geocoding;

import com.jobportal.jobportal.dtos.geocoding.CoordinatesDTO;
import com.jobportal.jobportal.dtos.geocoding.GeocodingResponseDTO;
import reactor.core.publisher.Mono;

public interface GeocodingService {

    CoordinatesDTO fetchCoordinates(String address);

}
