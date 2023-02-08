package com.bankingservice.server.repository;

import com.bankingservice.server.controller.MemberLoginForm;
import com.bankingservice.server.domain.Member;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class InmemoryMemberRepository implements MemberRepository {

    private final Map<String, Member> data = new HashMap<>();

    @Override
    public Member findById(String id) {

        Member member = data.get(id);

        return member;
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
