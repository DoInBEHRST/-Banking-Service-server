package com.bankingservice.server.repository;

import com.bankingservice.server.domain.Member;

public interface MemberRepository {

    Member save(Member member);

    Member findById(String id);

    Member findByIdAndPw(String id, String pw);
}
