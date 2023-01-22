package com.bankingservice.server.repository;

import com.bankingservice.server.controller.MemberLoginForm;
import com.bankingservice.server.domain.Member;

public interface MemberRepository {

    Member findByIdAndPw(MemberLoginForm memberLoginForm);
}
