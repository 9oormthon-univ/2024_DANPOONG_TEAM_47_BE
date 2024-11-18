package univ.goormthon.kongju.domain.member.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import univ.goormthon.kongju.domain.member.dto.response.ProfileInfo;
import univ.goormthon.kongju.domain.member.entity.Member;
import univ.goormthon.kongju.domain.member.service.MemberService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/kongju/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(HttpSession session) {

        Member member = (Member) session.getAttribute("member");

        ProfileInfo profileInfo = memberService.getProfile(member);
        return ResponseEntity.ok(profileInfo);
    }

}