package univ.goormthon.kongju.global.auth.kakao.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import univ.goormthon.kongju.domain.member.entity.Member;
import univ.goormthon.kongju.domain.member.service.MemberService;
import univ.goormthon.kongju.global.auth.kakao.dto.KakaoProfileInfoResponse;
import univ.goormthon.kongju.global.auth.kakao.dto.KakaoToken;
import univ.goormthon.kongju.global.auth.kakao.service.KakaoAuthService;

import java.net.URI;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/kongju")
@Tag(name = "카카오 로그인", description = "카카오 로그인 API")
public class KakaoAuthController {

    private final KakaoAuthService kakaoAuthService;
    private final MemberService memberService;

    @Value("${frontend.domain}")
    private String frontendDomain;

    @Operation(summary = "카카오 로그인", description = "카카오 로그인")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "카카오 로그인창으로 리다이렉트")
    })
    @GetMapping("/login")
    public ResponseEntity<?> kakaoLogin() {
        String kakaoAuthUrl = "https://kauth.kakao.com/oauth/authorize?response_type=code"
                + "&client_id=" + kakaoAuthService.getClientId()
                + "&redirect_uri=" + kakaoAuthService.getRedirectUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(kakaoAuthUrl));
        return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
    }

    @Operation(summary = "카카오 로그인 콜백", description = "카카오 인증 서버로 code를 반환받고 code를 이용하여 access token을 발급받습니다.")
    @Parameters(value = {
            @Parameter(name = "code", description = "인가코드")
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "메인으로 리다이렉트")
    })
    @PostMapping("/auth/token")
    public ResponseEntity<?> kakaoCallback(@RequestBody String code, HttpSession session) {
        KakaoToken accessToken = kakaoAuthService.getAccessToken(code);
        log.info("accessToken: {}", accessToken.accessToken());

        // 프로필 조회
        KakaoProfileInfoResponse profileInfo = kakaoAuthService.getProfileInfo(accessToken.accessToken());
        log.info("profileInfo: {}", profileInfo);

        Member member = memberService.loadProfile(profileInfo);
        log.info("member: {}", member);

        session.setAttribute("member", member);
        // 메인으로 redirect
        // return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(frontendDomain)).build();
        return ResponseEntity.ok(session.getAttribute("member"));
    }

    // 로그아웃
    @Operation(summary = "카카오 로그아웃", description = "카카오 로그아웃")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "카카오 로그아웃창으로 리다이렉트")
    })
    @GetMapping("/logout")
    public ResponseEntity<?> kakaoLogout() {
        String kakaoLogoutUrl = "https://kauth.kakao.com/oauth/logout"
                + "?client_id=" + kakaoAuthService.getClientId()
                + "&logout_redirect_uri=" + kakaoAuthService.getLogoutRedirectUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(kakaoLogoutUrl));
        return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
    }
}