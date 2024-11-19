package univ.goormthon.kongju.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import univ.goormthon.kongju.domain.member.dto.response.ProfileInfo;
import univ.goormthon.kongju.domain.member.entity.Member;
import univ.goormthon.kongju.domain.member.repository.MemberRepository;
import univ.goormthon.kongju.global.auth.kakao.dto.KakaoProfileInfoResponse;
import univ.goormthon.kongju.global.exception.NotFoundException;
import univ.goormthon.kongju.global.exception.dto.ErrorCode;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Member loadProfile(KakaoProfileInfoResponse profileInfo) {
        Member member = findOrRegisterMember(profileInfo.kakaoAccount().email(), profileInfo);
        return updateProfileIfChanged(member, profileInfo);
    }

    public Member findOrRegisterMember(String email, KakaoProfileInfoResponse response) {
        return memberRepository.findByEmail(email)
                .orElseGet(() -> registerMember(response));
    }

    private Member registerMember(KakaoProfileInfoResponse response) {
        Member member = Member.builder()
                .kakaoId(response.id())
                .email(response.kakaoAccount().email())
                .nickname(response.kakaoAccount().profile().nickname())
                .profileImage(response.kakaoAccount().profile().profileImageUrl())
                .build();
        return memberRepository.save(member);
    }

    private Member updateProfileIfChanged(Member member, KakaoProfileInfoResponse profileInfo) {
        boolean isChanged = false;
        String nickname = profileInfo.kakaoAccount().profile().nickname();
        String profileImage = profileInfo.kakaoAccount().profile().profileImageUrl();

        if(!member.getNickname().equals(nickname)) {
            member.setNickname(nickname);
            isChanged = true;
        }

        if(!member.getProfileImage().equals(profileImage)) {
            member.setProfileImage(profileImage);
            isChanged = true;
        }

        return isChanged ? memberRepository.save(member) : member;
    }

    @Transactional
    public ProfileInfo getProfile(Member member) {
        Member currentMember = memberRepository.findById(member.getId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.MEMBER_NOT_FOUND));

        return ProfileInfo.builder()
                .memberId(currentMember.getId())
                .nickname(currentMember.getNickname())
                .profileImage(currentMember.getProfileImage())
                .build();
    }
}
