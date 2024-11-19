package univ.goormthon.kongju.global.auth.kakao.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record KakaoProfileInfoResponse(
        @JsonProperty("id") Long id,
        @JsonProperty("connected_at") String connectedAt,
        @JsonProperty("kakao_account") KakaoAccount kakaoAccount
) {
    public record KakaoAccount(
            @JsonProperty("profile") KakaoProfile profile,
            @JsonProperty("email") String email
    ) {
        public record KakaoProfile(
                @JsonProperty("nickname") String nickname,
                @JsonProperty("thumbnail_image_url") String thumbnailImageUrl,
                @JsonProperty("profile_image_url") String profileImageUrl
        ) { }
    }
}