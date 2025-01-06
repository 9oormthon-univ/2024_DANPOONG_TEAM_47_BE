package univ.goormthon.kongju.global.jwt.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtProvider {

    private final SecretKey key = Jwts.SIG.HS256.key().build();
    private final long validityInMilliseconds = 1000 * 60 * 60; // 1시간

    public String generateToken(Authentication authentication) {
        String email = authentication.getName();

        return Jwts.builder()
                .subject(email)
                .signWith(key)
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + validityInMilliseconds))
                .compact();
    }

    public Claims getClaims(String token) {
    }

    public boolean validateToken(String token) {
    }

    public Authentication getAuthentication(String token) {
    }


}
