package univ.goormthon.kongju.member.service;

import org.springframework.stereotype.Service;
import univ.goormthon.kongju.member.domain.Member;
import univ.goormthon.kongju.member.utils.MemberFinder;


@Service
public class MemberService {

    private final MemberFinder memberFinder;

    public MemberService(MemberFinder memberFinder) {
        this.memberFinder = memberFinder;
    }

    public Member getMember(String email) {
        return memberFinder.findByEmail(email);
    }
}
