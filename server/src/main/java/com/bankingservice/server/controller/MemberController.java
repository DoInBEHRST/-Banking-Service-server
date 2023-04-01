package com.bankingservice.server.controller;

import com.bankingservice.server.dto.MemberLoginForm;
import com.bankingservice.server.dto.MemberSignupForm;
import com.bankingservice.server.dto.UserInfoDTO;
import com.bankingservice.server.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserInfoDTO> login(@RequestBody MemberLoginForm memberLoginForm) {
        UserInfoDTO member = memberService.login(memberLoginForm.getId(), memberLoginForm.getPw());
        return ResponseEntity.ok(member);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserInfoDTO> signup(@RequestBody MemberSignupForm memberSignupForm) {
        UserInfoDTO member = memberService.signup(memberSignupForm);
        return ResponseEntity.ok(member);
    }

    @PutMapping("/withdrawal")
    public ResponseEntity<Boolean> withdrawal(@RequestBody MemberLoginForm memberLoginForm) {
        Boolean result = memberService.withdrawal(memberLoginForm);
        return ResponseEntity.ok(result);
    }
}
