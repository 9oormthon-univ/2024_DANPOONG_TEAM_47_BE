package univ.goormthon.kongju.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "reservation")
public class ReservationEntity extends BaseTimeEntity {

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

    @Column(name = "total_fee")
    private Integer totalFee;

    @Builder
    public ReservationEntity(Long parkingId, Long memberId, Long vehicleId, LocalDate reservationDate, LocalTime startTime, LocalTime endTime, Integer totalFee) {
        this.parkingId = parkingId;
        this.memberId = memberId;
        this.vehicleId = vehicleId;
        this.reservationDate = reservationDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalFee = totalFee;
    }
}
