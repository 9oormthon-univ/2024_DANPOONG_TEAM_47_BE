package univ.goormthon.kongju.domain.review.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import univ.goormthon.kongju.global.entity.BaseTimeEntity;

@Entity
@Getter
@NoArgsConstructor
public class Review extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "member_id")
    private Long memberId;

    @JoinColumn(name = "parking_id")
    private Long parkingId;

    @Column(name = "content")
    private String content;

    @Column(name = "rating")
    private int rating;

    @Builder
    public Review(Long memberId, Long parkingId, String content, int rating) {
        this.memberId = memberId;
        this.parkingId = parkingId;
        this.content = content;
        this.rating = rating;
    }

}
