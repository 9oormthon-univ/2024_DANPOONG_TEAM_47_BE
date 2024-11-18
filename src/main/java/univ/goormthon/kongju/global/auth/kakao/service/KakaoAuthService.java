package univ.goormthon.kongju.global.auth.kakao.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import univ.goormthon.kongju.global.auth.kakao.dto.KakaoProfileInfoResponse;
import univ.goormthon.kongju.global.auth.kakao.dto.KakaoToken;

@Slf4j
@Getter
@Service
@RequiredArgsConstructor
public class KakaoAuthService {

    @Value("${kakao.client-id}")
    private String clientId;

    @Value("${kakao.redirect-uri}")
    private String redirectUri;

    @Value("${kakao.logout-redirect-uri}")
    private String logoutRedirectUri;

    private final RestTemplate restTemplate;
    public KakaoToken getAccessToken(String code) {
        String tokenUrl = "https://kauth.kakao.com/oauth/token";

        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // 바디 설정 (폼 데이터)
        MultiValueMap<String, String> bodyParams = new LinkedMultiValueMap<>();
        bodyParams.add("grant_type", "authorization_code");
        bodyParams.add("client_id", clientId);
        bodyParams.add("redirect_uri", redirectUri);
        bodyParams.add("code", code);

        HttpEntity<?> request = new HttpEntity<>(bodyParams, headers);

        KakaoToken response = restTemplate.postForObject(tokenUrl, request, KakaoToken.class);

        return response;
    }

    // 프로필 정보 조회
    public KakaoProfileInfoResponse getProfileInfo(String accessToken) {
        String profileUrl = "https://kapi.kakao.com/v2/user/me";

        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Bearer " + accessToken);

        HttpEntity<?> request = new HttpEntity<>(headers);

        KakaoProfileInfoResponse response = restTemplate.postForObject(profileUrl, request, KakaoProfileInfoResponse.class);

        return response;
    }
}
