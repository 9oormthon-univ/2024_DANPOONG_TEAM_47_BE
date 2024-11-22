package univ.goormthon.kongju.domain.vehicle.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import univ.goormthon.kongju.domain.member.entity.Member;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_type")
    private VehicleType vehicleType;

    @Column(name = "vehicle_number", nullable = false)
    private String vehicleNumber;

    @Builder
    public Vehicle(Member member, VehicleType vehicleType, String vehicleNumber) {
        this.member = member;
        this.vehicleType = vehicleType;
        this.vehicleNumber = vehicleNumber;
    }

    public void update(String type, String number) {
        this.vehicleType = VehicleType.of(type);
        this.vehicleNumber = number;
    }
}
