package univ.goormthon.kongju.domain.member.dto.response;

import lombok.Builder;

@Builder
public record ProfileInfo(Long memberId,
                          String nickname,
                          String profileImage) {
}
