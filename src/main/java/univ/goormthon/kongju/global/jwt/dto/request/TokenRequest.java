package univ.goormthon.kongju.global.jwt.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record TokenRequest(
        @JsonProperty("accessToken") String accessToken,
        @JsonProperty("refreshToken") String refreshToken,
        @JsonProperty("tokenType") String tokenType,
        @JsonProperty("idToken") String idToken,
        @JsonProperty("accessTokenExpiresAt") String accessTokenExpiresAt,
        @JsonProperty("refreshTokenExpiresAt") String refreshTokenExpiresAt,
        @JsonProperty("accessTokenExpiresIn") String accessTokenExpiresIn,
        @JsonProperty("refreshTokenExpiresIn") String refreshTokenExpiresIn,
        @JsonProperty("scopes") List<String> scopes
) {
}
