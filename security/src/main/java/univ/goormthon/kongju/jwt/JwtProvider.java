package univ.goormthon.kongju.jwt;

import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtProvider {

    private final SecretKey secretKey;

    public JwtProvider(SecretKey secretKey) {
        this.secretKey = secretKey;
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
}
