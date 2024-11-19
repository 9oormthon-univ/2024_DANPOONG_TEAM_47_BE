package univ.goormthon.kongju.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;
import univ.goormthon.kongju.global.entity.BaseTimeEntity;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "kakao_id")
    private Long kakaoId;

    @Column(name = "nickname")
    @Setter
    private String nickname;

    @Column(name = "email")
    private String email;

    @Column(name = "profile_image")
    @Setter
    private String profileImage;

    @Builder
    public Member(Long kakaoId, String nickname, String email, String profileImage) {
        this.kakaoId = kakaoId;
        this.nickname = nickname;
        this.email = email;
        this.profileImage = profileImage;
    }

}