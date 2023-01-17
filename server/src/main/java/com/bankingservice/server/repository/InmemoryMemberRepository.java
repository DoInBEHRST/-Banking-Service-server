package com.bankingservice.server.repository;

import com.bankingservice.server.controller.MemberForm;
import com.bankingservice.server.domain.Member;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class InmemoryMemberRepository implements MemberRepository{

    private final Map<String, Member> data = new HashMap<>();

    @Override
    public Member findByIdAndPw(MemberForm memberForm) {

        String id = memberForm.getId();

        Member member = data.get(id);
        System.out.println(member);

        return null;
    }
}
