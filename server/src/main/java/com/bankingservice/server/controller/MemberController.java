package com.bankingservice.server.controller;

import com.bankingservice.server.domain.Member;
import com.bankingservice.server.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Member> login(MemberLoginForm memberLoginForm) {
        Member member = memberService.login(memberLoginForm.getId(), memberLoginForm.getPw());
        return ResponseEntity.ok(member);
    }

    @PostMapping("/signup")
    public ResponseEntity<Member> signup(MemberSignupForm memberSignupForm) {
        Member newMember = Member.builder()
            .id(memberSignupForm.getId())
            .pw(memberSignupForm.getPw())
            .build();

        if (memberSignupForm.isPrt()) {
            newMember.setPrtId(memberSignupForm.getPrtId());
        }

        Member member = memberService.signup(newMember);

        return ResponseEntity.ok(member);
    }

    @PutMapping("/withdrawal")
    public ResponseEntity<Boolean> withdrawal(String id) {
        Boolean result = memberService.withdrawal(id);
        return ResponseEntity.ok(result);
    }
}
