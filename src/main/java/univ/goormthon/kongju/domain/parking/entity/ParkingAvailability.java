package univ.goormthon.kongju.domain.parking.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "parking_availability")
public class ParkingAvailability {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "availability_id")
    private Long id;

    @JoinColumn(name = "parking_id")
    private Long parkingId;

    @Column(name = "available_day")
    @Enumerated(EnumType.STRING)
    private AvailableDay availableDay;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @Builder
    public ParkingAvailability(Long parkingId, AvailableDay availableDay, LocalTime startTime, LocalTime endTime) {
        this.parkingId = parkingId;
        this.availableDay = availableDay;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
