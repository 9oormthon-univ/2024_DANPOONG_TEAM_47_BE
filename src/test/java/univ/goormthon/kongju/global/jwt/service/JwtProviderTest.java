package univ.goormthon.kongju.global.jwt.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import univ.goormthon.kongju.global.jwt.dto.request.TokenRequest;
import univ.goormthon.kongju.global.jwt.dto.response.TokenResponse;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@RequiredArgsConstructor
class JwtProviderTest {

    @Autowired
    private final JwtProvider jwtProvider;

    @Test
    void secretKey() {
        assertNotNull(jwtProvider.secretKey());
    }

    @Test
    void issueJwtToken_ShouldGenerateValidTokens() {
        // given
        TokenRequest tokenRequest = TokenRequest.builder()
                .accessToken("test-access-token")
                .build();

        // when
        TokenResponse tokenResponse = jwtProvider.issueJwtToken(tokenRequest);

        // then
        assertNotNull(tokenResponse.accessToken());
        assertNotNull(tokenResponse.refreshToken());
    }
}