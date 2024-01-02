package com.bankingservice.server.repository;

import com.bankingservice.server.entity.Member;
import org.springframework.data.repository.Repository;

public interface JpaMemberRepository extends Repository<Member, Long>, MemberRepository {

    @Override
    Member save(Member member);

    @Override
    Member findByID(String id);

    @Override
    Member findByIDAndPW(String id, String pw);

    @Override
    Member findByMBNO(Long MBNO);
}
