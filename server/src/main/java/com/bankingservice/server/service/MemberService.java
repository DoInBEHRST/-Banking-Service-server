package com.bankingservice.server.service;

import com.bankingservice.server.controller.MemberForm;
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


    public Member login(MemberForm memberForm){

        Member member = memberRepository.findByIdAndPw(memberForm);
        if(member == null){
            throw new IllegalStateException("아이디 혹은 비밀번호가 다릅니다.");
        }

        return member;
    }
}
