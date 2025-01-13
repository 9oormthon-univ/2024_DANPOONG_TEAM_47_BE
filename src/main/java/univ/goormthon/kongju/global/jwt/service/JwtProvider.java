package univ.goormthon.kongju.global.jwt.service;

import io.jsonwebtoken.Jwts;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import univ.goormthon.kongju.domain.member.entity.Member;
import univ.goormthon.kongju.domain.member.service.MemberService;
import univ.goormthon.kongju.global.jwt.dto.request.TokenRequest;
import univ.goormthon.kongju.global.jwt.dto.response.TokenResponse;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Service
public class JwtProvider {
    private final SecretKey secretKey = Jwts.SIG.HS256.key().build();
    private final MemberService memberService;
    private final WebClient webClient;

    public JwtProvider(MemberService memberService, WebClient.Builder webClientBuilder) {
        this.memberService = memberService;
        this.webClient = webClientBuilder.baseUrl("https://kapi.kakao.com").build();
    }

    @Bean
    public SecretKey secretKey() {
        return this.secretKey;
    }

    public TokenResponse issueJwtToken(TokenRequest tokenRequest) {
        String accessToken = generateAccessToken(tokenRequest.accessToken());
        String refreshToken = generateRefreshToken();

        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }


    private String generateAccessToken(String kakaoAccessToken) {
        String email = extractEmailFromKakaoAccessToken(kakaoAccessToken);

        return Jwts.builder()
                .issuer("shared-parking-service-Kongju")
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 3600 * 1000)) // 1시간
                .subject(email)
                .signWith(secretKey)
                .compact();

    }

    private String extractEmailFromKakaoAccessToken(String kakaoAccessToken) {
        var response = webClient.get()
                .uri("/v2/user/me")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + kakaoAccessToken)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        // 이메일 추출
        Map<String, Object> kakaoAccount = (Map<String, Object>) response.get("kakao_account");
        String email = (String) kakaoAccount.get("email");

        Member member = memberService.findOrRegisterMember(email, response);

        return email;
    }

    private String generateRefreshToken() {
        return Jwts.builder()
                .issuer("shared-parking-service-Kongju")
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 3600 * 1000 * 24 * 7)) // 1주일
                .signWith(secretKey)
                .compact();
    }

}
