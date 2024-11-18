package univ.goormthon.kongju.domain.parking.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import univ.goormthon.kongju.domain.member.entity.Member;
import univ.goormthon.kongju.global.entity.BaseTimeEntity;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "parking")
public class Parking extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parking_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

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

    @Builder
    public Parking(Member member, String name, String address, Double latitude, Double longitude, Integer carCapacity, Integer pmCapacity, String description, Integer rate) {
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
