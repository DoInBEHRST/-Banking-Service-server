package com.bankingservice.server.member.repository;

import com.bankingservice.server.member.entity.Member;

public interface MemberRepository {

    Member save(Member member);

    Member findByID(String id);

    Member findByIDAndPW(String id, String pw);

    Member findByMBNO(Long MBNO);
}
