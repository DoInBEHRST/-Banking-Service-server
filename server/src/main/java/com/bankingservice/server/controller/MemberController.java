package com.bankingservice.server.controller;

import com.bankingservice.server.domain.Member;
import com.bankingservice.server.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/login")
    public Member login(MemberLoginForm memberLoginForm) {
        return memberService.login(memberLoginForm);
    }

    @PostMapping("/signup")
    public Member signup(MemberSignupForm memberSignupForm) {
        return memberService.signup(memberSignupForm);
    }
}
