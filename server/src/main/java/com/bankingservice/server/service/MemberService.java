package com.bankingservice.server.service;

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


    public Member login(String id, String pw) {
        Member member = memberRepository.findByIdAndPw(id, pw);
        if (member == null) {
            throw new IllegalStateException("아이디 혹은 비밀번호가 다릅니다.");
        }

        return member;
    }

    public Member signup(Member member) {

        Member idCheckMember = memberRepository.findById(member.getId());
        if (idCheckMember != null) {
            throw new IllegalStateException("이미 존재하는 아이디 입니다.");
        }

        String prtId = member.getPrtId();
        if (prtId != null) {
            Member validParents = memberRepository.findById(prtId);
            if (validParents == null) {
                throw new IllegalStateException("잘못된 부모 아이디 입니다.");
            }
        }

        return memberRepository.save(member);
    }
}
