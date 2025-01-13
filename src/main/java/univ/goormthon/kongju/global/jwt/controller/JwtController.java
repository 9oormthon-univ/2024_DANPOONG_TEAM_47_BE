package univ.goormthon.kongju.global.jwt.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import univ.goormthon.kongju.global.jwt.dto.request.TokenRequest;
import univ.goormthon.kongju.global.jwt.dto.response.TokenResponse;
import univ.goormthon.kongju.global.jwt.service.JwtProvider;

@RestController
@RequestMapping("/api/v1/jwt")
@RequiredArgsConstructor
public class JwtController {

    private final JwtProvider jwtProvider;

    @PostMapping
    public ResponseEntity<TokenResponse> issueJwtToken(@RequestBody TokenRequest tokenRequest) {
        return ResponseEntity.ok(jwtProvider.issueJwtToken(tokenRequest));
    }
}
