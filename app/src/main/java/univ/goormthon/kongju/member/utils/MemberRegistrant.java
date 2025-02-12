package univ.goormthon.kongju.member.utils;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import univ.goormthon.kongju.entity.MemberEntity;
import univ.goormthon.kongju.member.domain.Member;
import univ.goormthon.kongju.repository.MemberRepository;

import java.util.Map;

@Component
public class MemberRegistrant {

    private final MemberRepository memberRepository;

    public MemberRegistrant(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Member register(Map<String, Object> userInfo) {
        MemberEntity memberEntity = MemberEntity.builder()
                .email((String) userInfo.get("email"))
                .nickname((String) userInfo.get("nickname"))
                .profileImage((String) userInfo.get("profile_image"))
                .build();

        return Member.of(memberRepository.save(memberEntity));
    }
}
