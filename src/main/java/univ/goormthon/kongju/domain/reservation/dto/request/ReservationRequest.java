package univ.goormthon.kongju.domain.reservation.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReservationRequest(
        @JsonProperty("parking_id") Long parkingId,
        @JsonProperty("vehicle_id") Long vehicleId,
        @JsonProperty("reservation_date") LocalDate reservationDate,
        @JsonProperty("start_time") LocalTime startTime,
        @JsonProperty("end_time") LocalTime endTime
) {
}
