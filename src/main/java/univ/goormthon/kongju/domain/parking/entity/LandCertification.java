package univ.goormthon.kongju.domain.parking.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "land_certification")
public class LandCertification{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "certification_id")
    private Long id;

    @JoinColumn(name = "parking_id")
    @Column(nullable = false)
    private Long parkingId;

    @Column(name = "image_url")
    private String imageUrl;

    @Builder
    public LandCertification(Long parkingId, String imageUrl) {
        this.parkingId = parkingId;
        this.imageUrl = imageUrl;
    }
}
