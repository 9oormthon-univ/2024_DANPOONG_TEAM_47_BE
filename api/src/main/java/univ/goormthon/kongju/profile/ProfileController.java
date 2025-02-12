package univ.goormthon.kongju.profile;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import univ.goormthon.kongju.jwt.MemberContext;
import univ.goormthon.kongju.member.domain.Member;
import univ.goormthon.kongju.member.service.MemberService;
import univ.goormthon.kongju.profile.dto.ProfileResponse;

@RestController
@RequestMapping("/api/v1/profiles")
public class ProfileController {

    private final MemberService memberService;

    public ProfileController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public ResponseEntity<ProfileResponse> getProfile() {
        String email = MemberContext.getCurrentMemberEmail();
        Member member = memberService.getMember(email);
        return ResponseEntity.ok(ProfileResponse.of(member));
    }

}
