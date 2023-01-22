package com.bankingservice.server.service;

import com.bankingservice.server.controller.MemberLoginForm;
import com.bankingservice.server.repository.InmemoryMemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
public class MemberServiceTest {

    MemberService memberService;
    InmemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new InmemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @Test
    void 없는_사람_로그인(){

        MemberLoginForm memberLoginForm = new MemberLoginForm();
        memberLoginForm.setId("권은비");
        memberLoginForm.setPw("1234");

        IllegalStateException e = assertThrows(IllegalStateException.class, ()-> memberService.login(memberLoginForm));

        assertThat(e.getMessage()).isEqualTo("아이디 혹은 비밀번호가 다릅니다.");

    }

}
