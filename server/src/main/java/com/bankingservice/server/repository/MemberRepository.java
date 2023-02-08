package com.bankingservice.server.repository;

import com.bankingservice.server.controller.MemberLoginForm;
import com.bankingservice.server.controller.MemberSignupForm;
import com.bankingservice.server.domain.Member;

public interface MemberRepository {

    Member save(MemberSignupForm memberSignupForm);

    Member findById(String id);

    Member findByIdAndPw(MemberLoginForm memberLoginForm);
}
