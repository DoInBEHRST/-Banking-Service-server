package com.bankingservice.server.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.bankingservice.server.constants.MemberStcd;
import com.bankingservice.server.domain.Member;
import com.bankingservice.server.repository.InmemoryMemberRepository;
import com.bankingservice.server.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class MemberServiceTest {

    MemberService memberService;
    MemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new InmemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @Test
    void 없는_사람_로그인() {

        Member member = Member.builder()
            .id("권은비")
            .pw("1234")
            .build();

        IllegalStateException e = assertThrows(IllegalStateException.class,
            () -> memberService.login(member.getId(), member.getPw()));

        assertThat(e.getMessage()).isEqualTo("아이디 혹은 비밀번호가 다릅니다.");

    }

    @Test
    void 회원가입_테스트() {

        Member member = Member.builder()
            .id("권은비")
            .pw("1234")
            .regNum("1234-1234")
            .prtId(null)
            .build();

        Member newMember = memberService.signup(member);

        assertThat(newMember.getId()).isEqualTo("권은비");
        assertThat(newMember.getPw()).isEqualTo("1234");
        assertThat(newMember.getRegNum()).isEqualTo("1234-1234");
        assertThat(newMember.getStcd()).isEqualTo(MemberStcd.STCD_USE);

    }

    @Test
    void 아이디_중복_테스트() {
        Member member = Member.builder()
            .id("권은비")
            .pw("1234")
            .regNum("1234-1234")
            .prtId(null)
            .build();

        Member member2 = Member.builder()
            .id("권은비")
            .pw("1234")
            .regNum("1234-1234")
            .prtId(null)
            .build();

        Member newMember = memberService.signup(member);

        IllegalStateException e = assertThrows(IllegalStateException.class,
            () -> memberService.signup(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 아이디 입니다.");

    }

    @Test
    void 부모아이디_유효성_테스트() {
        Member member = Member.builder()
            .id("권은비")
            .pw("1234")
            .regNum("1234-1234")
            .prtId("사쿠라")
            .build();

        IllegalStateException e = assertThrows(IllegalStateException.class,
            () -> memberService.signup(member));

        assertThat(e.getMessage()).isEqualTo("잘못된 부모 아이디 입니다.");

    }

    @Test
    void 회원탈퇴_테스트() {

        Member member = Member.builder()
            .id("권은비")
            .pw("1234")
            .regNum("1234-1234")
            .prtId(null)
            .build();

        memberService.signup(member);
        memberService.withdrawal(member.getId());

        IllegalStateException e = assertThrows(IllegalStateException.class,
            () -> memberService.login(member.getId(), member.getPw()));

        assertThat(e.getMessage()).isEqualTo("존재하지 않는 아이디 입니다.");


    }

}
