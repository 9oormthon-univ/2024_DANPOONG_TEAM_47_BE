package univ.goormthon.kongju.domain.vehicle.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record VehicleRequest (
        @JsonProperty("vehicle_type") String vehicleType,
        @JsonProperty("vehicle_number") String vehicleNumber
){
}
