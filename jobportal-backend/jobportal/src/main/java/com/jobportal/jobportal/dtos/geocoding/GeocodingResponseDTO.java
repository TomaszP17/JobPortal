package com.jobportal.jobportal.dtos.geocoding;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeocodingResponseDTO {
    private List<Result> results;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Result {
        private Geometry geometry;
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Geometry {
        private Location location;
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Location {
        private Double lat;
        private Double lng;
    }
}

