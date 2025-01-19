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
        return webClient.get()
                .uri("/v2/user/me")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }

    @Override
    public String getProviderName() {
        return "kakao";
    }
}
