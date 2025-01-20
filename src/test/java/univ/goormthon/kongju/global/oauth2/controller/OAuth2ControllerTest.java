package univ.goormthon.kongju.global.oauth2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import univ.goormthon.kongju.domain.member.entity.Member;
import univ.goormthon.kongju.domain.member.service.MemberService;
import univ.goormthon.kongju.global.config.SecurityConfig;
import univ.goormthon.kongju.global.exception.UnsupportedProviderException;
import univ.goormthon.kongju.global.exception.dto.ErrorCode;
import univ.goormthon.kongju.global.jwt.dto.request.TokenRequest;
import univ.goormthon.kongju.global.jwt.dto.response.TokenResponse;
import univ.goormthon.kongju.global.jwt.service.JwtProvider;
import univ.goormthon.kongju.global.oauth2.provider.OAuth2UserInfoProvider;
import univ.goormthon.kongju.global.oauth2.provider.OAuth2UserInfoProviderFactory;

import javax.crypto.SecretKey;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = OAuth2Controller.class)
@Import(SecurityConfig.class)
class OAuth2ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OAuth2UserInfoProviderFactory oAuth2UserInfoProviderFactory;

    @MockBean
    private JwtProvider jwtProvider;

    @MockBean
    private MemberService memberService;

    @MockBean
    private SecretKey secretKey;

    @Autowired
    private ObjectMapper objectMapper;
    @Test
    void testLogin_Success() throws Exception {
        // Mock 데이터 설정
        String provider = "kakao";
        String accessToken = "mock-access-token";
        String email = "test@example.com";
        TokenResponse jwt = TokenResponse.builder()
                .accessToken("mock-jwt")
                .refreshToken("mock-refresh-token")
                .build();

        Map<String, Object> userInfo = Map.of(
                "email", email,
                "name", "Test User",
                "profile_image", "https://example.com/profile.jpg"
        );

        OAuth2UserInfoProvider mockProvider = mock(OAuth2UserInfoProvider.class);
        when(oAuth2UserInfoProviderFactory.getProvider(provider)).thenReturn(mockProvider);
        when(mockProvider.getUserInfo(accessToken)).thenReturn(userInfo);
        when(memberService.findOrRegisterMember(userInfo)).thenReturn(
                Member.builder()
                        .email(email)
                        .nickname("Test User")
                        .profileImage("https://example.com/profile.jpg")
                        .build()
        );
        when(jwtProvider.issueJwtToken(email)).thenReturn(jwt);

        // 요청 본문 생성
        TokenRequest tokenRequest = TokenRequest.builder()
                .accessToken(accessToken).build();
        String requestBody = objectMapper.writeValueAsString(tokenRequest);

        // API 호출 및 검증
        mockMvc.perform(post("/api/v1/auth/login")
                        .param("provider", provider)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.access_token").value(jwt.accessToken()));
    }

    @Test
    void testLogin_InvalidProvider() throws Exception {
        // Mock 데이터 설정
        String provider = "invalid-provider";
        String accessToken = "mock-access-token";

        when(oAuth2UserInfoProviderFactory.getProvider(provider))
                .thenThrow(new UnsupportedProviderException(ErrorCode.UNSUPPORTED_PROVIDER));

        // 요청 본문 생성
        TokenRequest tokenRequest = TokenRequest.builder()
                .accessToken(accessToken).build();
        String requestBody = objectMapper.writeValueAsString(tokenRequest);

        // API 호출 및 검증
        mockMvc.perform(post("/api/v1/auth/login")
                        .param("provider", provider)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest());
    }
}