package univ.goormthon.kongju.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.web.SecurityFilterChain;
import univ.goormthon.kongju.global.oauth2.CustomOAuth2UserService;
import univ.goormthon.kongju.global.oauth2.CustomRequestEntityConverter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // 브라우저 환경이 아니므로 CSRF 보호 기능 비활성화
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/h2-console/**","/swagger-ui.html","/swagger-ui/**", "/api/kongju/oauth2/**", "/api/kongju/auth/**").permitAll()
                        .anyRequest().authenticated()
                )
                .headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOAuth2UserService))
                        .defaultSuccessUrl("/api/kongju/oauth2/success", true)
                        .tokenEndpoint(token -> token
                                .accessTokenResponseClient(accessTokenResponseClient())));
        return http.build();
    }

    @Bean
    public OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> accessTokenResponseClient() {
        DefaultAuthorizationCodeTokenResponseClient client = new DefaultAuthorizationCodeTokenResponseClient();
        client.setRequestEntityConverter(customRequestEntityConverter());
        return client;
    }

    @Bean
    public CustomRequestEntityConverter customRequestEntityConverter() {
        return new CustomRequestEntityConverter();
    }

}
