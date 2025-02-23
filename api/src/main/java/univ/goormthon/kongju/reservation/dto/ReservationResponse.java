package univ.goormthon.kongju.reservation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import univ.goormthon.kongju.reservation.domain.Reservation;

@Builder
public record ReservationResponse(
        @JsonProperty("id") Long id,
        @JsonProperty("parking_id") Long parkingId,
        @JsonProperty("member_id") Long memberId,
        @JsonProperty("reservation_date") String reservationDate,
        @JsonProperty("start_time") String startTime,
        @JsonProperty("end_time") String endTime,
        @JsonProperty("reservation_status") String reservationStatus,
        @JsonProperty("total_fee") Integer totalFee
) {

    public static ReservationResponse of(Reservation reservation) {
        return ReservationResponse.builder()
                .id(reservation.getId())
                .parkingId(reservation.getParkingId())
                .memberId(reservation.getMemberId())
                .reservationDate(String.valueOf(reservation.getReservationDate()))
                .startTime(String.valueOf(reservation.getStartTime()))
                .endTime(String.valueOf(reservation.getEndTime()))
                .totalFee(reservation.getTotalFee())
                .build();
    }
}
