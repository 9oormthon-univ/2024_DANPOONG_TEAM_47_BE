package univ.goormthon.kongju.domain.vehicle.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import univ.goormthon.kongju.domain.vehicle.entity.VehicleType;

@Builder
public record VehicleResponse(
        Long id,
        @JsonProperty("vehicle_type") VehicleType vehicleType,
        @JsonProperty("vehicle_number") String vehicleNumber
) {
}
