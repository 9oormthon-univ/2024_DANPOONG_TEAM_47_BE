package univ.goormthon.kongju.global.oauth2.provider;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Component
public class KakaoUserInfoProvider implements OAuth2UserInfoProvider {

    private final WebClient webClient;

    public KakaoUserInfoProvider(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://kapi.kakao.com").build();
    }

    @Override
    public Map<String, Object> getUserInfo(String accessToken) {
        var response = webClient.get()
                .uri("/v2/user/me")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        // 카카오 사용자 정보 변환
        Map<String, Object> kakaoAccount = (Map<String, Object>) response.get("kakao_account");
        Map<String, Object> properties = (Map<String, Object>) response.get("properties");

        return Map.of(
                "id", response.get("id"),
                "email", kakaoAccount.get("email"),
                "nickname", properties.get("nickname"),
                "profile_image", properties.get("profile_image")
        );
    }

    @Override
    public String getProviderName() {
        return "kakao";
    }
}
