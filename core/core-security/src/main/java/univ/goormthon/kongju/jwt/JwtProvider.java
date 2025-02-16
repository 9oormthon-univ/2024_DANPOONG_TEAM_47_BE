package univ.goormthon.kongju.jwt;

import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtProvider {

    private final SecretKey secretKey;
    private final JwtValidator jwtValidator;

    public JwtProvider(SecretKey secretKey, JwtValidator jwtValidator) {
        this.secretKey = secretKey;
        this.jwtValidator = jwtValidator;
    }
    public String issueAccessToken(String email) {
        return Jwts.builder()
                .issuer("shared-parking-service-Kongju")
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 3600 * 1000)) // 1시간
                .subject(email)
                .signWith(secretKey)
                .compact();
    }

    public String issueRefreshToken(String email) {
        String refreshToken = Jwts.builder()
                .issuer("shared-parking-service-Kongju")
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 3600 * 1000 * 24 * 7)) // 1주
                .subject(email)
                .signWith(secretKey)
                .compact();

        jwtValidator.saveRefreshToken(TokenEntity.builder()
                .email(email)
                .refreshToken(refreshToken)
                .build());

        return refreshToken;
    }

    public boolean isRefreshTokenValid(String refreshToken) {
        return jwtValidator.isRefreshTokenValid(refreshToken);
    }

    public void invalidateRefreshToken(String email) {
        jwtValidator.invalidateRefreshToken(email);
    }

    public String getEmailFromToken(String refreshToken) {
        return jwtValidator.getEmailFromToken(refreshToken);
    }
}
