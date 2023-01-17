package com.bankingservice.server.service;

import com.bankingservice.server.controller.MemberForm;
import com.bankingservice.server.domain.Member;
import com.bankingservice.server.repository.InmemoryMemberRepository;
import com.bankingservice.server.repository.MemberRepository;
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

        MemberForm memberForm = new MemberForm();
        memberForm.setId("권은비");
        memberForm.setPw("1234");

        IllegalStateException e = assertThrows(IllegalStateException.class, ()-> memberService.login(memberForm));

        assertThat(e.getMessage()).isEqualTo("아이디 혹은 비밀번호가 다릅니다.");

    }

}
