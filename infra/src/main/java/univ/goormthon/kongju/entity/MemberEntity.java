package univ.goormthon.kongju.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Table(name = "member")
public class MemberEntity extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "email")
    private String email;

    @Column(name = "profile_image")
    private String profileImage;

    public MemberEntity() {
    }

    @Builder
    public MemberEntity(String nickname, String email, String profileImage) {
        this.nickname = nickname;
        this.email = email;
        this.profileImage = profileImage;
    }
}
