package univ.goormthon.kongju.member.service;

import org.springframework.stereotype.Service;
import univ.goormthon.kongju.member.domain.Member;
import univ.goormthon.kongju.member.utils.MemberFinder;
import univ.goormthon.kongju.member.utils.MemberRegistrant;

import java.util.Map;


@Service
public class MemberService {

    private final MemberFinder memberFinder;
    private final MemberRegistrant memberRegistrant;

    public MemberService(MemberFinder memberFinder, MemberRegistrant memberRegistrant) {
        this.memberFinder = memberFinder;
        this.memberRegistrant = memberRegistrant;
    }

    public Member getMember(Map<String, Object> userInfo) {
        String email = (String) userInfo.get("email");
        if(!memberFinder.isExistByEmail(email)){
            registerMember(userInfo);
        }
        return memberFinder.findByEmail(email);
    }

    private void registerMember(Map<String, Object> userInfo) {
        memberRegistrant.register(userInfo);
    }
}
