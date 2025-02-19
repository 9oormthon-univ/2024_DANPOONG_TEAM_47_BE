package univ.goormthon.kongju.entity.parking;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import univ.goormthon.kongju.entity.BaseTimeEntity;
import univ.goormthon.kongju.entity.MemberEntity;

@Entity
@Getter
@Table(name = "parking")
public class ParkingEntity extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parking_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id", nullable = false)
    private MemberEntity member;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "car_capacity")
    private Integer carCapacity;

    @Column(name = "pm_capacity")
    private Integer pmCapacity;

    @Column(name = "description")
    private String description;

    // 30분당 요금
    @Column(name = "rate")
    private Integer rate;

    public ParkingEntity() {
    }

    @Builder
    public ParkingEntity(MemberEntity member, String name, String address, Double latitude, Double longitude, Integer carCapacity, Integer pmCapacity, String description, Integer rate) {
        this.member = member;
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.carCapacity = carCapacity;
        this.pmCapacity = pmCapacity;
        this.description = description;
        this.rate = rate;
    }
}
