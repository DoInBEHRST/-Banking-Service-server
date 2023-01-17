package com.bankingservice.server.repository;

import com.bankingservice.server.controller.MemberForm;
import com.bankingservice.server.domain.Member;

public interface MemberRepository {

    Member findByIdAndPw(MemberForm memberForm);
}
