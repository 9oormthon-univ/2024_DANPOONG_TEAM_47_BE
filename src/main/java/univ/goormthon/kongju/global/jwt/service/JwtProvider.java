package univ.goormthon.kongju.global.jwt.service;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import univ.goormthon.kongju.global.jwt.dto.response.TokenResponse;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtProvider {

    private final SecretKey secretKey;

    public TokenResponse issueJwtToken(String email) {
        String accessToken = generateAccessToken(email);
        String refreshToken = generateRefreshToken();

        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }


    private String generateAccessToken(String email) {
        return Jwts.builder()
                .issuer("shared-parking-service-Kongju")
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 3600 * 1000)) // 1시간
                .subject(email)
                .signWith(secretKey)
                .compact();

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
