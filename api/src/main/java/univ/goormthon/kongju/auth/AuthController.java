package univ.goormthon.kongju.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import univ.goormthon.kongju.auth.dto.TokenRequest;
import univ.goormthon.kongju.auth.dto.TokenResponse;
import univ.goormthon.kongju.client.provider.OAuth2UserInfoProvider;
import univ.goormthon.kongju.client.provider.OAuth2UserInfoProviderFactory;
import univ.goormthon.kongju.jwt.JwtProvider;
import univ.goormthon.kongju.member.domain.Member;
import univ.goormthon.kongju.member.service.MemberService;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final JwtProvider jwtProvider;
    private final OAuth2UserInfoProviderFactory oAuth2UserInfoProviderFactory;
    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<TokenResponse> issueToken(@RequestParam String provider,
                                                    @RequestBody TokenRequest tokenRequest) {
        OAuth2UserInfoProvider providerInstance = oAuth2UserInfoProviderFactory.getProvider(provider);
        Map<String, Object> userInfo = providerInstance.getUserInfo(tokenRequest.accessToken());
        Member member = memberService.getMember(userInfo);

        return ResponseEntity.ok(TokenResponse.builder()
                .accessToken(jwtProvider.issueAccessToken(member.getEmail())).build());
    }
}
