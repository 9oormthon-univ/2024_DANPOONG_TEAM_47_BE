package univ.goormthon.kongju.global.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kongju")
public class AuthController {

    @GetMapping("/oauth2/success")
    public ResponseEntity<?> getUserInfo(@AuthenticationPrincipal OAuth2User oAuth2User) {
        return ResponseEntity.ok(new UserInfo(oAuth2User.getName(), oAuth2User.getAttributes()));
    }
}
