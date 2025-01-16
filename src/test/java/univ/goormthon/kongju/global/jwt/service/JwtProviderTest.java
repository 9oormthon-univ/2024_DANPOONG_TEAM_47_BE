package univ.goormthon.kongju.global.jwt.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class JwtProviderTest {

    @Autowired
    private final JwtProvider jwtProvider;

    @Test
    void secretKey() {
        assertNotNull(jwtProvider.secretKey());
    }

    @Test
    void issueJwtToken() {
    }
}