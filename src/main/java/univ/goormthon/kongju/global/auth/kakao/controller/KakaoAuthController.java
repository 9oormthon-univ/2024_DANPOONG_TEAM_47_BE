package univ.goormthon.kongju.global.auth.kakao.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
public class KakaoAuthController {

    private final KakaoAuthService kakaoAuthService;
    private final MemberService memberService;

    //  카카오 로그인 페이지로 Redirect
    @GetMapping("/login")
    public ResponseEntity<?> kakaoLogin() {
        String kakaoAuthUrl = "https://kauth.kakao.com/oauth/authorize?response_type=code"
                + "&client_id=" + kakaoAuthService.getClientId()
                + "&redirect_uri=" + kakaoAuthService.getRedirectUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(kakaoAuthUrl));
        return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
    }

    @GetMapping("/auth/kakao/callback")
    public ResponseEntity<?> kakaoCallback(@RequestParam("code") String code, HttpSession session) {
        KakaoToken accessToken = kakaoAuthService.getAccessToken(code);
        log.info("accessToken: {}", accessToken.accessToken());

        // 프로필 조회
        KakaoProfileInfoResponse profileInfo = kakaoAuthService.getProfileInfo(accessToken.accessToken());
        log.info("profileInfo: {}", profileInfo);

        Member member = memberService.loadProfile(profileInfo);
        log.info("member: {}", member);

        session.setAttribute("member", member);

        // 메인으로 redirect
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("/")).build();
    }

    // 로그아웃
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