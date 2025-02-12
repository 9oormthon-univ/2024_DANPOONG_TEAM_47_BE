package univ.goormthon.kongju.profile.dto;

import lombok.Builder;
import univ.goormthon.kongju.member.domain.Member;

@Builder
public record ProfileResponse(
        String nickname, String email, String imageUrl
) {

    public static ProfileResponse of(Member member) {
        return ProfileResponse.builder()
                .nickname(member.getNickname())
                .email(member.getEmail())
                .imageUrl(member.getImageUrl())
                .build();
    }
}
