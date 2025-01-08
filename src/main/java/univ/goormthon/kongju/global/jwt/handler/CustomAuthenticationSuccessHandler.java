package univ.goormthon.kongju.global.jwt.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import univ.goormthon.kongju.global.jwt.service.JwtProvider;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtProvider jwtProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String acceptHeader = request.getHeader("Accept");
        String token = jwtProvider.generateToken(authentication);

        response.setHeader("Authorization", "Bearer " + token);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("Access Token: " + token);
    }
}
