package com.bankingservice.server.controller;

import com.bankingservice.server.domain.Member;
import com.bankingservice.server.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/login")
    public Member login(MemberLoginForm memberLoginForm) {
        return memberService.login(memberLoginForm.getId(), memberLoginForm.getPw());
    }

    @PostMapping("/signup")
    public Member signup(MemberSignupForm memberSignupForm) {
        Member newMember = Member.builder()
            .id(memberSignupForm.getId())
            .pw(memberSignupForm.getPw())
            .build();

        if (memberSignupForm.isPrt()) {
            newMember.setPrtId(memberSignupForm.getPrtId());
        }

        return memberService.signup(newMember);
    }

    @PutMapping("/withdrawal")
    public boolean withdrawal(String id) {
        return memberService.withdrawal(id);
    }
}
