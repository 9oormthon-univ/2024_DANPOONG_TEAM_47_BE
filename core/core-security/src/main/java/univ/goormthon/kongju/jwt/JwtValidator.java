package univ.goormthon.kongju.jwt;

import org.springframework.stereotype.Component;

@Component
public class JwtValidator {
    private final TokenRepository tokenRepository;

    public JwtValidator(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public void saveRefreshToken(TokenEntity tokenEntity) {
        tokenRepository.save(tokenEntity);
    }

    public void invalidateRefreshToken(String email) {
        tokenRepository.deleteById(email);
    }

    public boolean isRefreshTokenValid(String refreshToken) {
        return tokenRepository.existsTokenEntityByRefreshToken(refreshToken);
    }

    public String getEmailFromToken(String refreshToken) {
        return tokenRepository.findByRefreshToken(refreshToken).get().getEmail();
    }
}
