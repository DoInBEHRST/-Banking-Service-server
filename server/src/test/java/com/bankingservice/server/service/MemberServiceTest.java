package com.bankingservice.server.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.bankingservice.server.controller.MemberLoginForm;
import com.bankingservice.server.controller.MemberSignupForm;
import com.bankingservice.server.domain.Member;
import com.bankingservice.server.repository.InmemoryMemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class MemberServiceTest {

    MemberService memberService;
    InmemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new InmemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @Test
    void 없는_사람_로그인() {

        MemberLoginForm memberLoginForm = MemberLoginForm.builder()
            .id("권은비")
            .pw("1234")
            .build();

        IllegalStateException e = assertThrows(IllegalStateException.class,
            () -> memberService.login(memberLoginForm));

        assertThat(e.getMessage()).isEqualTo("아이디 혹은 비밀번호가 다릅니다.");

    }

    @Test
    void 회원가입_테스트() {

        MemberSignupForm newMemberForm = MemberSignupForm.builder()
            .id("권은비")
            .pw("1234")
            .regNum("1234-1234")
            .isPrt(false)
            .prtId(null)
            .build();

        Member newMember = memberService.signup(newMemberForm);

        assertThat(newMember.getId()).isEqualTo("권은비");
        assertThat(newMember.getPw()).isEqualTo("1234");
        assertThat(newMember.getRegNum()).isEqualTo("1234-1234");

    }

    @Test
    void 아이디_중복_테스트() {
        MemberSignupForm newMemberForm = MemberSignupForm.builder()
            .id("권은비")
            .pw("1234")
            .regNum("1234-1234")
            .isPrt(false)
            .prtId(null)
            .build();

        MemberSignupForm newMemberForm2 = MemberSignupForm.builder()
            .id("권은비")
            .pw("1234")
            .regNum("1234-1234")
            .isPrt(false)
            .prtId(null)
            .build();

        Member newMember = memberService.signup(newMemberForm);

        IllegalStateException e = assertThrows(IllegalStateException.class,
            () -> memberService.signup(newMemberForm2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 아이디 입니다.");

    }

    @Test
    void 부모아이디_유효성_테스트() {
        MemberSignupForm newMemberForm = MemberSignupForm.builder()
            .id("권은비")
            .pw("1234")
            .regNum("1234-1234")
            .isPrt(true)
            .prtId("사쿠라")
            .build();

        IllegalStateException e = assertThrows(IllegalStateException.class,
            () -> memberService.signup(newMemberForm));

        assertThat(e.getMessage()).isEqualTo("잘못된 부모 아이디 입니다.");

    }

}
