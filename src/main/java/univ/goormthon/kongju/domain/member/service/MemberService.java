package univ.goormthon.kongju.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import univ.goormthon.kongju.domain.member.dto.response.ProfileInfo;
import univ.goormthon.kongju.domain.member.entity.Member;
import univ.goormthon.kongju.domain.member.repository.MemberRepository;
import univ.goormthon.kongju.global.exception.NotFoundException;
import univ.goormthon.kongju.global.exception.dto.ErrorCode;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Member findOrRegisterMember(Map<String, Object> userInfo) {
        return memberRepository.findByEmail((String) userInfo.get("email"))
                .orElseGet(() -> registerMember(userInfo));
    }

    private Member registerMember(Map<String, Object> userInfo) {
        Member member = Member.builder()
                .kakaoId(Long.valueOf(userInfo.get("id").toString()))
                .email((String) userInfo.get("email"))
                .nickname((String) userInfo.get("nickname"))
                .profileImage((String) userInfo.get("profile_image"))
                .build();

        return memberRepository.save(member);
    }

    @Transactional
    public ProfileInfo getProfile(String email) {
        Member currentMember = memberRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(ErrorCode.MEMBER_NOT_FOUND));

        return ProfileInfo.builder()
                .memberId(currentMember.getId())
                .nickname(currentMember.getNickname())
                .profileImage(currentMember.getProfileImage())
                .build();
    }
}
