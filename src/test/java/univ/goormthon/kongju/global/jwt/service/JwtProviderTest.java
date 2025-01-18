package univ.goormthon.kongju.global.jwt.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import univ.goormthon.kongju.global.jwt.dto.request.TokenRequest;
import univ.goormthon.kongju.global.jwt.dto.response.TokenResponse;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class JwtProviderTest {

    @Autowired
    private JwtProvider jwtProvider;

    @Test
    @DisplayName("요청에 따라 access token과 refresh token을 발급한다.")
    void issueJwtToken() {
        // given
        TokenRequest tokenRequest = TokenRequest.builder()
                .accessToken("test-access-token")
                .build();

        // when
        TokenResponse tokenResponse = jwtProvider.issueJwtToken(tokenRequest);

        // then
        assertNotNull(tokenResponse);
    }

}