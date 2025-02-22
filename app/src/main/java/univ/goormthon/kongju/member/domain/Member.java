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
    private String profileImage;

    @Builder
    public Member(Long id, Long kakaoId, String nickname, String email, String profileImage) {
        this.id = id;
        this.kakaoId = kakaoId;
        this.nickname = nickname;
        this.email = email;
        this.profileImage = profileImage;
    }

    public static Member of(MemberEntity memberEntity) {
        return Member.builder()
                .id(memberEntity.getId())
                .nickname(memberEntity.getNickname())
                .email(memberEntity.getEmail())
                .profileImage(memberEntity.getProfileImage())
                .build();
    }

    public MemberEntity toEntity() {
        return MemberEntity.builder()
                .nickname(nickname)
                .email(email)
                .profileImage(profileImage)
                .build();
    }
}
