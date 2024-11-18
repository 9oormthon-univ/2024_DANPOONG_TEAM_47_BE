package univ.goormthon.kongju.domain.parking.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "parking_image")
public class ParkingImage{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;

    @JoinColumn(name = "parking_id")
    @Column(nullable = false)
    private Long parkingId;

    @Column(name = "image_url")
    private String imageUrl;

    @Builder
    public ParkingImage(Long parkingId, String imageUrl) {
        this.parkingId = parkingId;
        this.imageUrl = imageUrl;
    }
}
