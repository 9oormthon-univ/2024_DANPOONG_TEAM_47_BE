package univ.goormthon.kongju.member.domain;

import lombok.Builder;
import lombok.Getter;
import univ.goormthon.kongju.entity.MemberEntity;

@Getter
public class Member {

    private Long id;
    private Long kakaoId;
    private String nickname;
    private String email;
    private String imageUrl;

    @Builder
    public Member(Long id, Long kakaoId, String nickname, String email, String imageUrl) {
        this.id = id;
        this.kakaoId = kakaoId;
        this.nickname = nickname;
        this.email = email;
        this.imageUrl = imageUrl;
    }

    public static Member of(MemberEntity memberEntity) {
        return Member.builder()
                .id(memberEntity.getId())
                .kakaoId(memberEntity.getKakaoId())
                .nickname(memberEntity.getNickname())
                .email(memberEntity.getEmail())
                .imageUrl(memberEntity.getImageUrl())
                .build();
    }
}
