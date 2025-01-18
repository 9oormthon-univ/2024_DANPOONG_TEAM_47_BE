package univ.goormthon.kongju.global.jwt.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import univ.goormthon.kongju.global.jwt.dto.request.TokenRequest;
import univ.goormthon.kongju.global.jwt.service.JwtProvider;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = JwtController.class)
class JwtControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtProvider jwtProvider;

    @Test
    @DisplayName("POST 요청을 통해 access token과 refresh token을 JSON 형태로 출력하고 200 상태코드를 반환한다.")
    void issueJwtToken() throws Exception {
        // given
        TokenRequest tokenRequest = TokenRequest.builder()
                .accessToken("test-access-token")
                .build();

        // when & then
        mockMvc.perform(post("/api/v1/jwt")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(tokenRequest.toString())
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

}