package univ.goormthon.kongju.reservation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import univ.goormthon.kongju.reservation.domain.Reservation;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReservationRequest(
        @JsonProperty("parking_id") Long parkingId,
        @JsonProperty("reservation_date") LocalDate reservationDate,
        @JsonProperty("start_time") LocalTime startTime,
        @JsonProperty("end_time") LocalTime endTime
) {

    public Reservation toDomain() {
        return Reservation.builder()
                .parkingId(parkingId)
                .reservationDate(reservationDate)
                .startTime(startTime)
                .endTime(endTime)
                .build();
    }
}
