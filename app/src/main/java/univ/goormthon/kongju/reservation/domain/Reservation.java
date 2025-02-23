package univ.goormthon.kongju.reservation.domain;

import lombok.Builder;
import lombok.Getter;
import univ.goormthon.kongju.entity.ReservationEntity;
import univ.goormthon.kongju.member.domain.Member;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
public class Reservation {

    private Long id;
    private Long parkingId;
    private Long memberId;
    private LocalDate reservationDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private Integer totalFee;

    @Builder
    public Reservation(Long id, Long parkingId, Long memberId, LocalDate reservationDate, LocalTime startTime, LocalTime endTime, Integer totalFee) {
        this.id = id;
        this.parkingId = parkingId;
        this.memberId = memberId;
        this.reservationDate = reservationDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalFee = totalFee;
    }

    public static Reservation of(ReservationEntity reservationEntity) {
        return Reservation.builder()
                .id(reservationEntity.getId())
                .parkingId(reservationEntity.getParkingId())
                .memberId(reservationEntity.getMemberId())
                .reservationDate(reservationEntity.getReservationDate())
                .startTime(reservationEntity.getStartTime())
                .endTime(reservationEntity.getEndTime())
                .totalFee(reservationEntity.getTotalFee())
                .build();
    }

    public ReservationEntity toEntity(Member member) {
        return ReservationEntity.builder()
                .parkingId(parkingId)
                .memberId(member.getId())
                .reservationDate(reservationDate)
                .startTime(startTime)
                .endTime(endTime)
                .totalFee(totalFee)
                .build();
    }
}
