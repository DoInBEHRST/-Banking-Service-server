package com.bankingservice.server.repository;

import com.bankingservice.server.controller.MemberLoginForm;
import com.bankingservice.server.controller.MemberSignupForm;
import com.bankingservice.server.domain.Member;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class InmemoryMemberRepository implements MemberRepository {

    private final Map<String, Member> data = new HashMap<>();


    @Override
    public Member save(MemberSignupForm memberSignupForm) {

        Member newMember = Member.builder()
            .id(memberSignupForm.getId())
            .pw(memberSignupForm.getPw())
            .regNum(memberSignupForm.getRegNum())
            .build();

        // 나중에 부모 아이디 있으면 넣어주기
//        if (memberSignupForm.isPrt()){
//            Member parents = data.get(memberSignupForm.getPrtId());
//            
//        }

        data.put(newMember.getId(), newMember);

        return newMember;
    }

    @Override
    public Member findById(String id) {
        return data.get(id);
    }

    @Override
    public Member findByIdAndPw(MemberLoginForm memberLoginForm) {

        String id = memberLoginForm.getId();

        Member member = data.get(id);
        if (member == null) {
            return null;
        }

        String pw = memberLoginForm.getPw();
        if(!member.getPw().equals(pw)){
            return null;
        }

        return member;
    }
}
