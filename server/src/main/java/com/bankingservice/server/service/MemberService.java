package com.bankingservice.server.service;

import com.bankingservice.server.controller.MemberLoginForm;
import com.bankingservice.server.controller.MemberSignupForm;
import com.bankingservice.server.domain.Member;
import com.bankingservice.server.repository.InmemoryMemberRepository;
import com.bankingservice.server.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(InmemoryMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    public Member login(MemberLoginForm memberLoginForm) {

        Member member = memberRepository.findByIdAndPw(memberLoginForm);
        if (member == null) {
            throw new IllegalStateException("아이디 혹은 비밀번호가 다릅니다.");
        }

        return member;
    }

    public Member signup(MemberSignupForm memberSignupForm) {

        Member idCheckMember = memberRepository.findById(memberSignupForm.getId());
        if (idCheckMember != null) {
            throw new IllegalStateException("이미 존재하는 아이디 입니다.");
        }

        if (memberSignupForm.isPrt()) {
            Member validParents = memberRepository.findById(memberSignupForm.getPrtId());
            if (validParents == null) {
                throw new IllegalStateException("잘못된 부모 아이디 입니다.");
            }
        }

        return memberRepository.save(memberSignupForm);
    }
}
