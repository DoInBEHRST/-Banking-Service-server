package com.bankingservice.server.repository;

import com.bankingservice.server.entity.Member;

public interface MemberRepository {

    Member save(Member member);

    Member findByID(String id);

    Member findByIDAndPW(String id, String pw);

    Member findByMBNO(Long MBNO);
}
