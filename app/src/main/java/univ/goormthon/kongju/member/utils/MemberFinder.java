package univ.goormthon.kongju.member.utils;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import univ.goormthon.kongju.entity.MemberEntity;
import univ.goormthon.kongju.exception.dto.ErrorCode;
import univ.goormthon.kongju.member.domain.Member;
import univ.goormthon.kongju.member.exception.MemberNotFoundException;
import univ.goormthon.kongju.repository.MemberRepository;

@Component
public class MemberFinder {

    private final MemberRepository memberRepository;

    public MemberFinder(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional(readOnly = true)
    public Member findByEmail(String email) {
        MemberEntity member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberNotFoundException(ErrorCode.MEMBER_NOT_FOUND));

        return Member.of(member);
    }
}
