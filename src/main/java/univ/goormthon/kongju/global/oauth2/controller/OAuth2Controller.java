package univ.goormthon.kongju.global.oauth2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import univ.goormthon.kongju.domain.member.entity.Member;
import univ.goormthon.kongju.domain.member.service.MemberService;
import univ.goormthon.kongju.global.jwt.dto.request.TokenRequest;
import univ.goormthon.kongju.global.jwt.service.JwtProvider;
import univ.goormthon.kongju.global.oauth2.provider.OAuth2UserInfoProvider;
import univ.goormthon.kongju.global.oauth2.provider.OAuth2UserInfoProviderFactory;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class OAuth2Controller {

    private final JwtProvider jwtProvider;
    private final OAuth2UserInfoProviderFactory oAuth2UserInfoProviderFactory;
    private final MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String provider,
                                @RequestBody TokenRequest tokenRequest) {
        OAuth2UserInfoProvider providerInstance = oAuth2UserInfoProviderFactory.getProvider(provider);
        Map<String, Object> userInfo = providerInstance.getUserInfo(tokenRequest.accessToken());

        Member member = memberService.findOrRegisterMember(userInfo);
        return ResponseEntity.ok(jwtProvider.issueJwtToken(member.getEmail()));
    }
}
