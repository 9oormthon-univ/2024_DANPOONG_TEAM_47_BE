package univ.goormthon.kongju.client.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import univ.goormthon.kongju.client.provider.OAuth2UserInfoProvider;
import univ.goormthon.kongju.client.provider.OAuth2UserInfoProviderFactory;
import univ.goormthon.kongju.jwt.JwtProvider;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final JwtProvider jwtProvider;
    private final OAuth2UserInfoProviderFactory oAuth2UserInfoProviderFactory;

//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestParam String provider,
//                                   @RequestBody TokenRequest tokenRequest) {
//        OAuth2UserInfoProvider providerInstance = oAuth2UserInfoProviderFactory.getProvider(provider);
//        Map<String, Object> userInfo = providerInstance.getUserInfo(tokenRequest.accessToken());
//
//        // Member member = memberService.findOrRegisterMember(userInfo);
//        // return ResponseEntity.ok(jwtProvider.issueJwtToken(member.getEmail()));
//    }

    @PostMapping("/login")
    public ResponseEntity<?> login() {
        return ResponseEntity.ok(jwtProvider.issueAccessToken("test@example.com"));
    }
}
