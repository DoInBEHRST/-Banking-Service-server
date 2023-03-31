package com.bankingservice.server.repository;

import com.bankingservice.server.domain.Member;
import java.util.HashMap;
import java.util.Map;

public class InmemoryMemberRepository implements MemberRepository {

    private final Map<String, Member> data = new HashMap<>();


    @Override
    public Member save(Member member) {

        data.put(member.getId(), member);

        return member;
    }

    @Override
    public Member findById(String id) {
        return data.get(id);
    }

    @Override
    public Member findByIdAndPw(String id, String pw) {

        Member member = data.get(id);
        if (member == null) {
            return null;
        }

        if (!member.getPw().equals(pw)) {
            return null;
        }

        return member;
    }
}
