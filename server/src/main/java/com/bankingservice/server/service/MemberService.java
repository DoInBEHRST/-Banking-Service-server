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


    public boolean login(MemberForm memberForm){

        Member member = memberRepository.findByIdAndPw(memberForm);

        return true;
    }
}
