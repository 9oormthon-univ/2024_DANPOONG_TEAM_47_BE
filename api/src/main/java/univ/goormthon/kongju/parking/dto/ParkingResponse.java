package univ.goormthon.kongju.parking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import univ.goormthon.kongju.parking.domain.Parking;

@Builder
public record ParkingResponse(
        @JsonProperty("id") Long id,
        @JsonProperty("name") String name,
        @JsonProperty("address") String address,
        @JsonProperty("latitude") Double latitude,
        @JsonProperty("longitude") Double longitude,
        @JsonProperty("carCapacity") Integer carCapacity,
        @JsonProperty("pmCapacity") Integer pmCapacity,
        @JsonProperty("description") String description,
        @JsonProperty("rate") Integer rate
) {
    public static ParkingResponse of(Parking parking) {
        return ParkingResponse.builder()
                .id(parking.getId())
                .name(parking.getName())
                .address(parking.getAddress())
                .latitude(parking.getLatitude())
                .longitude(parking.getLongitude())
                .carCapacity(parking.getCarCapacity())
                .pmCapacity(parking.getPmCapacity())
                .description(parking.getDescription())
                .rate(parking.getRate())
                .build();
    }
}
