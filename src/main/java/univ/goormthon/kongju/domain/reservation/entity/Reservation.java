package univ.goormthon.kongju.domain.reservation.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import univ.goormthon.kongju.global.entity.BaseTimeEntity;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "reservation")
public class Reservation extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;

    @JoinColumn(name = "parking_id")
    @Column(nullable = false)
    private Long parkingId;

    @JoinColumn(name = "member_id")
    @Column(nullable = false)
    private Long memberId;

    @JoinColumn(name = "vehicle_id")
    @Column(nullable = false)
    private Long vehicleId;

    @Column(name = "reservation_date")
    private LocalDate reservationDate;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @Setter
    @Column(name = "reservation_status")
    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    @Column(name = "total_fee")
    private Integer totalFee;

    @Builder
    public Reservation(Long parkingId, Long memberId, Long vehicleId, LocalDate reservationDate, LocalTime startTime, LocalTime endTime, ReservationStatus reservationStatus, Integer totalFee) {
        this.parkingId = parkingId;
        this.memberId = memberId;
        this.vehicleId = vehicleId;
        this.reservationDate = reservationDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.reservationStatus = reservationStatus;
        this.totalFee = totalFee;
    }
}
