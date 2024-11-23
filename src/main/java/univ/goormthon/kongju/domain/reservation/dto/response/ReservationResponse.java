package univ.goormthon.kongju.domain.reservation.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record ReservationResponse(
        @JsonProperty("id") Long id,
        @JsonProperty("parking_id") Long parkingId,
        @JsonProperty("member_id") Long memberId,
        @JsonProperty("vehicle_id") Long vehicleId,
        @JsonProperty("reservation_date") String reservationDate,
        @JsonProperty("start_time") String startTime,
        @JsonProperty("end_time") String endTime,
        @JsonProperty("reservation_status") String reservationStatus,
        @JsonProperty("total_fee") Integer totalFee
) {
}
